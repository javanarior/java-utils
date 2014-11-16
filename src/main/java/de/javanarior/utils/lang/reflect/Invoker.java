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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Invocation helper.
 */
public final class Invoker {

    private Invoker() {
    }

    /**
     * Invoke the default/non-arg constructor from {@code toInstanciate}.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @return a new instance of toInstanciate
     */
    public static Object invokeConstructor(Class<?> toInstanciate) {
        try {
            return toInstanciate.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new ReflectionException("Could not invoke default constructor, please check if accessable", exception);
        }
    }

    /**
     * Invoke the constructor from {@code toInstanciate} with the parameter
     * specified by {@code parameterTypes} and provides {@code parameterValues}
     * as parameters.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param parameterType
     *            - the parameter type of the constructor to use
     * @param parameterValue
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static Object invokeConstructor(Class<?> toInstanciate, Class<?> parameterType, Object parameterValue) {
        return invokeConstructor(toInstanciate, new Class[] { parameterType }, new Object[] { parameterValue });
    }

    /**
     * Invoke the constructor from {@code toInstanciate} with the parameter
     * specified by {@code parameterTypes} and provides {@code parameterValues}
     * as parameters.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param parameterTypes
     *            - the parameter types of the constructor to use
     * @param parameterValues
     *            - the values to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static Object invokeConstructor(Class<?> toInstanciate, Class<?>[] parameterTypes, Object[] parameterValues) {
        Constructor<?> constructor;
        try {
            constructor = toInstanciate.getConstructor(parameterTypes);
            return constructor.newInstance(parameterValues);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException exception) {
            throw new ReflectionException(
                            "Could not invoke constructor, please check if constructor exists and the parameters fit",
                            exception);
        }
    }

}
