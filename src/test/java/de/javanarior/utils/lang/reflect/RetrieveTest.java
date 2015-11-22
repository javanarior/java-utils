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

@Resource(name = "RetrieveTest")
@Testeria("attributeValue")
public class RetrieveTest {

    @Test
    public void testAnnotationValueOnClass() {
        Object attributeValue = Retrieve.annotationValueOnClass(Resource.class, "name", RetrieveTest.class);
        assertThat(attributeValue, notNullValue());
        assertThat((String)attributeValue, equalTo("RetrieveTest"));
    }

    @Test
    public void testAnnotationValueOnClassWithWrongNamedAttribute() {
        try {
            Retrieve.annotationValueOnClass(Resource.class, "wrongName", RetrieveTest.class);
            assertThat("ReflectionException is expected", false);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            equalTo("Attribute 'wrongName' not found on 'javax.annotation.Resource'"));
        }
    }

    @Test
    public void testAnnotationValueOnClassWithDefault() {
        Object attributeValue = Retrieve.annotationValueOnClass(Testeria.class, RetrieveTest.class);
        assertThat(attributeValue, notNullValue());
        assertThat((String)attributeValue, is("attributeValue"));
    }

    @Test
    public void testAnnotationValueOnClassWithDefaultAnnotationIsMissing() {
        try {
            Retrieve.annotationValueOnClass(Test.class, RetrieveTest.class);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            equalTo("Annotation 'interface org.testng.annotations.Test' not found in"
                                            + " Class 'de.javanarior.utils.lang.reflect.RetrieveTest'"));
        }
    }

    @Test(alwaysRun = true)
    public void testAnnotationValueOnMethod() {
        Object attributeValue = Retrieve.annotationValueOnMethod(Test.class, "alwaysRun", RetrieveTest.class,
                        "testAnnotationValueOnMethod");
        assertThat(attributeValue, notNullValue());
        assertThat((Boolean)attributeValue, is(Boolean.TRUE));
    }

    @Test
    public void testAnnotationValueOnMethodWithParameter() {
        Object attributeValue = Retrieve.annotationValueOnMethod(Resource.class, "name", RetrieveTest.class,
                        "methodForTestingPurpuse");
        assertThat(attributeValue, notNullValue());
        assertThat((String)attributeValue, equalTo("attributeValueWithParameter"));
    }

    @Resource(name = "attributeValueWithParameter")
    public void methodForTestingPurpuse(String unused) {
        /* Do nothing */
    }

    @Test
    public void testAnnotationValueOnMethodWithParameterPolymorphismString() {
        Object attributeValue = Retrieve.annotationValueOnMethod(Resource.class, "name", RetrieveTest.class,
                        "methodForTestingPurposePolymorphism", String.class);
        assertThat(attributeValue, notNullValue());
        assertThat((String)attributeValue, equalTo("attributeValueString"));
    }

    @Test
    public void testAnnotationValueOnMethodWithParameterPolymorphismInteger() {
        Object attributeValue = Retrieve.annotationValueOnMethod(Resource.class, "name", RetrieveTest.class,
                        "methodForTestingPurposePolymorphism", Integer.class);
        assertThat(attributeValue, notNullValue());
        assertThat((String)attributeValue, equalTo("attributeValueInteger"));
    }

    @Resource(name = "attributeValueString")
    public void methodForTestingPurposePolymorphism(String unused) {
        /* Do nothing */
    }

    @Resource(name = "attributeValueInteger")
    public void methodForTestingPurposePolymorphism(int unused) {
        /* Do nothing */
    }

    @Test
    public void testAnnotationValueOnMethodAnnotationIsMissing() {
        try {
            Retrieve.annotationValueOnMethod(Resource.class, RetrieveTest.class,
                            "testAnnotationValueOnMethodAnnotationIsMissing");
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            equalTo("Annotation 'interface javax.annotation.Resource' not found on Method "
                                            + "'public void de.javanarior.utils.lang.reflect."
                                            + "RetrieveTest.testAnnotationValueOnMethodAnnotationIsMissing()'"));
        }
    }

    @Test
    public void testAnnotationValueOnMethodWithWrongMethodName() {
        try {
            Retrieve.annotationValueOnMethod(Test.class, "alwaysRun", RetrieveTest.class, "testWrongName");
            assertThat("ReflectionException is expected", false);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            /* CHECKSTYLE:OFF */
                            equalTo("Method name 'testWrongName' not found in Class 'de.javanarior.utils.lang.reflect.RetrieveTest'"));
            /* CHECKSTYLE:ON */
        }
    }

    @Test
    public void testAnnotationValueOnMethodWithWrongAttributeName() {
        try {
            Retrieve.annotationValueOnMethod(Test.class, "wrongName", RetrieveTest.class, "testAnnotationValueOnMethod");
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
        Object attributeValue = Retrieve.annotationValueOnMethod(Testeria.class, RetrieveTest.class,
                        "testAnnotationValueOnMethodWithDefault");
        assertThat(attributeValue, notNullValue());
        assertThat((String)attributeValue, is("attributeValue"));
    }

    @Test
    @Testeria("attributeValue")
    public void testAnnotationValueOnMethodWrongMethodName() {
        try {
            Retrieve.annotationValueOnMethod(Testeria.class, RetrieveTest.class, "wrongMethodName");
            assertThat("ReflectionException is expected", false);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                            /* CHECKSTYLE:OFF */
                            equalTo("Method name 'wrongMethodName' not found in "
                                            + "Class 'de.javanarior.utils.lang.reflect.RetrieveTest'"));
            /* CHECKSTYLE:ON */
        }
    }

    @Test
    public void testAnnotationValueOnParameter() {
        Object annotationValueOnParameter = Retrieve.annotationValueOnParameter(Testeria.class, "value", RetrieveTest.class, "methodForTestingPurpuseParameter", "unused");
        assertThat(annotationValueOnParameter, notNullValue());
        assertThat((String)annotationValueOnParameter, is("attributeValueOnParameter"));
    }

    @Test
    public void testAnnotationValueOnParameterWithMissingAnnoation() {
        try {
            Retrieve.annotationValueOnParameter(Resource.class, "value",
                            RetrieveTest.class, "methodForTestingPurpuseParameter", "unused");
            assertThat("ReflectionException is expected", false);
        } catch (ReflectionException exception) {
            assertThat("ReflectionException is thrown", true);
            assertThat(exception.getMessage(),
                 equalTo("Annotation 'javax.annotation.Resource' not found in Parameter list of Method "
                                 + "'public void de.javanarior.utils.lang.reflect."
                                 + "RetrieveTest.methodForTestingPurpuseParameter(java.lang.String)'"));
        }
    }

    public void methodForTestingPurpuseParameter(@Testeria("attributeValueOnParameter") String unused) {

    }
}
