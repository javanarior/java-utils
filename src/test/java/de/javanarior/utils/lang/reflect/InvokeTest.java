/*
 * Copyright (C) 2014 Sven von Pluto - javanarior (a) gmail dot com
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

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@Testeria("attributeValue")
public class InvokeTest {

    public void testInvokeConstructorNoArguments() {
        Object instance = Invoke.invokeConstructor(Object.class);
        Assert.assertNotNull(instance);
    }

    @Test(expectedExceptions = ReflectionException.class)
    public void testInvokeConstructorNoArgumentsWithPrivateConstructor() {
        Invoke.invokeConstructor(WithPrivateConstructor.class);
    }

    public void testInvokeConstructorWithSingleArgument() {
        Object instance = Invoke.invokeConstructor(ReflectionException.class, String.class, "TestMe");
        Assert.assertNotNull(instance);
        Assert.assertEquals(((Throwable)instance).getMessage(), "TestMe");
    }

    public void testInvokeConstructorWithArgumentArray() {
        Object instance = Invoke.invokeConstructor(ReflectionException.class,
                        new Class[] { String.class, Throwable.class },
                        new Object[] { "TestMe", new RuntimeException("TestMeRootException") });
        Assert.assertNotNull(instance);
        Assert.assertEquals(((Throwable)instance).getMessage(), "TestMe");
        Assert.assertEquals(((Throwable)instance).getCause().getMessage(), "TestMeRootException");
    }

    @Test(expectedExceptions = ReflectionException.class)
    public void testInvokeConstructorWithWrongArguments() {
        Invoke.invokeConstructor(ReflectionException.class,
                        new Class[] { String.class, Throwable.class },
                        new Object[] { "TestMe", "" });

    }

    public void testInvokeConstructorBoolean() {
        Boolean instance = Invoke.invokeConstructor(Boolean.class, Boolean.TRUE);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance.booleanValue());
    }

    public void testInvokeConstructorBooleanPrimitive() {
        Boolean instance = Invoke.invokeConstructor(Boolean.class, true);
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance.booleanValue());
    }

    public void testInvokeConstructorByte() {
        Byte instance = Invoke.invokeConstructor(Byte.class, Byte.valueOf("2"));
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance, Byte.valueOf("2"));
    }

    public void testInvokeConstructorBytePrimitive() {
        Byte instance = Invoke.invokeConstructor(Byte.class, (byte)2);
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance.byteValue(), (byte)2);
    }

    public void testInvokeConstructorChar() {
        Character instance = Invoke.invokeConstructor(Character.class, 'A');
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance, Character.valueOf('A'));
    }

    public void testInvokeConstructorCharPrimitive() {
        Character instance = Invoke.invokeConstructor(Character.class, Character.valueOf('A'));
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance.charValue(), 'A');
    }

    public void testInvokeConstructorDouble() {
        Double instance = Invoke.invokeConstructor(Double.class, Double.valueOf("2.2"));
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance, Double.valueOf("2.2"));
    }

    public void testInvokeConstructorDoublePrimitive() {
        Double instance = Invoke.invokeConstructor(Double.class, 2.2);
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance.doubleValue(), 2.2, 0.01);
    }

    public void testInvokeConstructorInt() {
        Integer instance = Invoke.invokeConstructor(Integer.class, Integer.valueOf(2));
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance, Integer.valueOf(2));
    }

    public void testInvokeConstructorIntPrimitive() {
        Integer instance = Invoke.invokeConstructor(Integer.class, 2);
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance.intValue(), 2);
    }

    public void testInvokeConstructorLong() {
        Long instance = Invoke.invokeConstructor(Long.class, Long.valueOf(2));
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance, Long.valueOf(2));
    }

    public void testInvokeConstructorLongPrimitive() {
        Long instance = Invoke.invokeConstructor(Long.class, 2L);
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance.longValue(), 2L);
    }

    public void testInvokeConstructorShort() {
        Short instance = Invoke.invokeConstructor(Short.class, Short.valueOf("2"));
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance, Short.valueOf("2"));
    }

    public void testInvokeConstructorShortPrimitive() {
        Short instance = Invoke.invokeConstructor(Short.class, (short)2);
        Assert.assertNotNull(instance);
        Assert.assertEquals(instance.shortValue(), 2);
    }

    public void testInvokeAnnotation() {
        Testeria annotation = InvokeTest.class.getAnnotation(Testeria.class);
        Object attributeValue = Invoke.invokeAnnotation(annotation, "value");
        assertThat((String)attributeValue, equalTo("attributeValue"));
    }

    private static final class WithPrivateConstructor {
        private WithPrivateConstructor() {
        }
    }

}
