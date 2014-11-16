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

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class InvokerTest {

    public void testInvokeConstructorNoArguments() {
        Object instance = Invoker.invokeConstructor(Object.class);
        Assert.assertNotNull(instance);
    }

    @Test(expectedExceptions = ReflectionException.class)
    public void testInvokeConstructorNoArgumentsWithPrivateConstructor() {
        Invoker.invokeConstructor(WithoutConstructor.class);
    }

    public void testInvokeConstructorWithSingleArgument() {
        Object instance = Invoker.invokeConstructor(ReflectionException.class, String.class, "TestMe");
        Assert.assertNotNull(instance);
        Assert.assertEquals(((Throwable)instance).getMessage(), "TestMe");
    }

    public void testInvokeConstructorWithArgumentArray() {
        Object instance = Invoker.invokeConstructor(ReflectionException.class,
                        new Class[] { String.class, Throwable.class },
                        new Object[] { "TestMe", new RuntimeException("TestMeRootException") });
        Assert.assertNotNull(instance);
        Assert.assertEquals(((Throwable)instance).getMessage(), "TestMe");
        Assert.assertEquals(((Throwable)instance).getCause().getMessage(), "TestMeRootException");
    }

    @Test(expectedExceptions = ReflectionException.class)
    public void testInvokeConstructorWithWrongArguments() {
        Invoker.invokeConstructor(ReflectionException.class,
                        new Class[] { String.class, Throwable.class },
                        new Object[] { "TestMe", "" });

    }

    private static final class WithoutConstructor {
        private WithoutConstructor() {

        }
    }
}
