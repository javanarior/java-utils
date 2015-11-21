/*
 * Copyright (C) 2015 Sven von Pluto - javanarior (a) gmail dot com
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
package de.javanarior.utils.lang.reflect;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import javax.annotation.Resource;

import org.testng.annotations.Test;

@Test(alwaysRun = true)
@Testeria("attributeValue")
public class RetrieveTest {

    @Test
    public void testAnnotationValueOnClass() {
        Object attributeValue = Retrieve.annotationValueOnClass(RetrieveTest.class, Test.class, "alwaysRun");
        assertThat(attributeValue, notNullValue());
        assertThat((Boolean)attributeValue, is(Boolean.TRUE));
    }

    @Test
    public void testAnnotationValueOnClassWithWrongNamedAttribute() {
        try {
            Retrieve.annotationValueOnClass(RetrieveTest.class, Test.class, "wrongName");
            assertThat("ReflectionException is expected", false);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            equalTo("Attribute 'wrongName' not found on 'org.testng.annotations.Test'"));
        }
    }

    @Test
    public void testAnnotationValueOnClassWithDefault() {
        Object attributeValue = Retrieve.annotationValueOnClass(RetrieveTest.class, Testeria.class);
        assertThat(attributeValue, notNullValue());
        assertThat((String)attributeValue, is("attributeValue"));
    }

    @Test
    public void testAnnotationValueOnClassWithDefaultAnnotationIsMissing() {
        try {
            Retrieve.annotationValueOnClass(RetrieveTest.class, Resource.class);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            /* CHECKSTYLE:OFF */
                            equalTo("Annotation 'interface javax.annotation.Resource' not found on Class 'de.javanarior.utils.lang.reflect.RetrieveTest'"));
            /* CHECKSTYLE:ON */
        }
    }

    @Test(alwaysRun = true)
    public void testAnnotationValueOnMethod() {
        Object attributeValue = Retrieve.annotationValueOnMethod(RetrieveTest.class, "testAnnotationValueOnMethod",
                        Test.class,
                        "alwaysRun");
        assertThat(attributeValue, notNullValue());
        assertThat((Boolean)attributeValue, is(Boolean.TRUE));
    }

    public void testAnnotationValueOnMethodAnnotationIsMissing() {
        try {
            Retrieve.annotationValueOnMethod(RetrieveTest.class, "testAnnotationValueOnMethodAnnotationIsMissing",
                            Test.class);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            /* CHECKSTYLE:OFF */
                            equalTo("Annotation 'interface org.testng.annotations.Test' not found on Method 'public void de.javanarior.utils.lang.reflect.RetrieveTest.testAnnotationValueOnMethodAnnotationIsMissing()'"));
            /* CHECKSTYLE:ON */
        }
    }

    public void testAnnotationValueOnMethodWithWrongMethodName() {
        try {
            Retrieve.annotationValueOnMethod(RetrieveTest.class, "testWrongName", Test.class, "alwaysRun");
            assertThat("ReflectionException is expected", false);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            /* CHECKSTYLE:OFF */
                            equalTo("Method name 'testWrongName' not found in Class 'de.javanarior.utils.lang.reflect.RetrieveTest'"));
            /* CHECKSTYLE:ON */
        }
    }

    public void testAnnotationValueOnMethodWithWrongAttributeName() {
        try {
            Retrieve.annotationValueOnMethod(RetrieveTest.class, "testAnnotationValueOnMethod", Test.class, "wrongName");
            assertThat("ReflectionException is expected", false);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            equalTo("Attribute 'wrongName' not found on 'org.testng.annotations.Test'"));
        }
    }

    @Test
    @Testeria("attributeValue")
    public void testAnnotationValueOnMethodWithDefault() {
        Object attributeValue = Retrieve.annotationValueOnMethod(RetrieveTest.class,
                        "testAnnotationValueOnMethodWithDefault", Testeria.class);
        assertThat(attributeValue, notNullValue());
        assertThat((String)attributeValue, is("attributeValue"));
    }

    @Test
    @Testeria("attributeValue")
    public void testAnnotationValueOnMethodWrongMethodName() {
        try {
            Retrieve.annotationValueOnMethod(RetrieveTest.class, "wrongMethodName", Testeria.class);
            assertThat("ReflectionException is expected", false);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            /* CHECKSTYLE:OFF */
                            equalTo("Method name 'wrongMethodName' not found in Class 'de.javanarior.utils.lang.reflect.RetrieveTest'"));
            /* CHECKSTYLE:ON */
        }
    }

}
