/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.script

import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.descriptors.ScriptDescriptor
import org.jetbrains.kotlin.descriptors.annotations.Annotations
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.parsing.KotlinParserDefinition
import org.jetbrains.kotlin.psi.KtScript
import org.jetbrains.kotlin.resolve.descriptorUtil.module
import org.jetbrains.kotlin.serialization.deserialization.NotFoundClasses
import org.jetbrains.kotlin.serialization.deserialization.findNonGenericClassAcrossDependencies
import org.jetbrains.kotlin.storage.LockBasedStorageManager
import org.jetbrains.kotlin.types.*
import java.io.File
import java.lang.RuntimeException
import java.lang.UnsupportedOperationException
import kotlin.reflect.*
import kotlin.script.StandardScriptTemplate

open class KotlinScriptDefinition(val template: KClass<out Any>) {

    open val name: String = "Kotlin Script"

    // TODO: consider creating separate type (subtype? for kotlin scripts)
    open val fileType: LanguageFileType = KotlinFileType.INSTANCE

    open fun <TF> isScript(file: TF): Boolean =
            getFileName(file).endsWith(KotlinParserDefinition.STD_SCRIPT_EXT)

    open fun getScriptName(script: KtScript): Name =
        ScriptNameUtil.fileNameWithExtensionStripped(script, KotlinParserDefinition.STD_SCRIPT_EXT)

    open fun <TF> getDependenciesFor(file: TF, project: Project, previousDependencies: KotlinScriptExternalDependencies?): KotlinScriptExternalDependencies? = null
}

interface KotlinScriptExternalDependencies {
    val javaHome: String? get() = null
    val classpath: Iterable<File> get() = emptyList()
    val imports: Iterable<String> get() = emptyList()
    val sources: Iterable<File> get() = emptyList()
    val scripts: Iterable<File> get() = emptyList()
}

data class ScriptParameter(val name: Name, val type: KotlinType)

object StandardScriptDefinition : KotlinScriptDefinition(StandardScriptTemplate::class)

fun KotlinScriptDefinition.getScriptParameters(scriptDescriptor: ScriptDescriptor): List<ScriptParameter> =
        template.primaryConstructor?.parameters
                ?.map { ScriptParameter(Name.identifier(it.name!!), getKotlinTypeByKType(scriptDescriptor, it.type)) }
        ?: emptyList()

fun getKotlinType(scriptDescriptor: ScriptDescriptor, kClass: KClass<out Any>): KotlinType =
        getKotlinTypeByFqName(scriptDescriptor,
                              kClass.qualifiedName ?: throw RuntimeException("Cannot get FQN from $kClass"))

fun getKotlinTypeByFqName(scriptDescriptor: ScriptDescriptor, fqName: String): KotlinType =
        scriptDescriptor.module.findNonGenericClassAcrossDependencies(
                ClassId.topLevel(FqName(fqName)),
                NotFoundClasses(LockBasedStorageManager.NO_LOCKS, scriptDescriptor.module)
        ).defaultType

// TODO: support star projections
// TODO: support annotations on types and type parameters
// TODO: support type parameters on types and type projections
fun getKotlinTypeByKType(scriptDescriptor: ScriptDescriptor, kType: KType): KotlinType {
    val classifier = kType.classifier
    if (classifier !is KClass<*>)
        throw UnsupportedOperationException("Only classes are supported as parameters in script template: $classifier")

    val type = getKotlinType(scriptDescriptor, classifier)
    val typeProjections = kType.arguments.map { getTypeProjection(scriptDescriptor, it) }
    val isNullable = kType.isMarkedNullable

    return KotlinTypeFactory.simpleType(Annotations.EMPTY, type.constructor, typeProjections, isNullable)
}

private fun getTypeProjection(scriptDescriptor: ScriptDescriptor, kTypeProjection: KTypeProjection): TypeProjection {
    val kType = kTypeProjection.type ?: throw UnsupportedOperationException("Star projections are not supported")

    val type = getKotlinTypeByKType(scriptDescriptor, kType)

    val variance = when (kTypeProjection.variance) {
        KVariance.IN -> Variance.IN_VARIANCE
        KVariance.OUT -> Variance.OUT_VARIANCE
        KVariance.INVARIANT -> Variance.INVARIANT
        null -> throw UnsupportedOperationException("Star projections are not supported")
    }

    return TypeProjectionImpl(variance, type)
}