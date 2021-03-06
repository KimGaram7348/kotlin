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

package org.jetbrains.kotlin.idea.codeInsight.postfix;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/codeInsight/postfix")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class PostfixTemplateProviderTestGenerated extends AbstractPostfixTemplateProviderTest {
    public void testAllFilesPresentInPostfix() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/codeInsight/postfix"), Pattern.compile("^(.+)\\.kt$"), true);
    }

    @TestMetadata("assert.kt")
    public void testAssert() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/assert.kt");
        doTest(fileName);
    }

    @TestMetadata("else.kt")
    public void testElse() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/else.kt");
        doTest(fileName);
    }

    @TestMetadata("for.kt")
    public void testFor() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/for.kt");
        doTest(fileName);
    }

    @TestMetadata("if.kt")
    public void testIf() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/if.kt");
        doTest(fileName);
    }

    @TestMetadata("iter.kt")
    public void testIter() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/iter.kt");
        doTest(fileName);
    }

    @TestMetadata("notBoolean.kt")
    public void testNotBoolean() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/notBoolean.kt");
        doTest(fileName);
    }

    @TestMetadata("notString.kt")
    public void testNotString() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/notString.kt");
        doTest(fileName);
    }

    @TestMetadata("notnull.kt")
    public void testNotnull() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/notnull.kt");
        doTest(fileName);
    }

    @TestMetadata("null.kt")
    public void testNull() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/null.kt");
        doTest(fileName);
    }

    @TestMetadata("par.kt")
    public void testPar() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/par.kt");
        doTest(fileName);
    }

    @TestMetadata("return.kt")
    public void testReturn() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/return.kt");
        doTest(fileName);
    }

    @TestMetadata("sout.kt")
    public void testSout() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/sout.kt");
        doTest(fileName);
    }

    @TestMetadata("try.kt")
    public void testTry() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/try.kt");
        doTest(fileName);
    }

    @TestMetadata("val.kt")
    public void testVal() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/val.kt");
        doTest(fileName);
    }

    @TestMetadata("var.kt")
    public void testVar() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/var.kt");
        doTest(fileName);
    }

    @TestMetadata("when.kt")
    public void testWhen() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/when.kt");
        doTest(fileName);
    }

    @TestMetadata("while.kt")
    public void testWhile() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("idea/testData/codeInsight/postfix/while.kt");
        doTest(fileName);
    }
}
