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
 * Reflection Invocation helper.
 */
public final class Invoker {

    private Invoker() {
    }

    /**
     * Invoke the constructor from {@code toInstanciate} with the parameter
     * specified by {@code parameterTypes} and provides {@code parameterValues}
     * as parameters.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argumentTypes
     *            - the parameter types of the constructor to use
     * @param argument
     *            - the values to pass to the constructor
     * @return a new instance of toInstanciate
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeConstructor(Class<T> toInstanciate, Class<?>[] argumentTypes, Object[] argument) {
        Constructor<?> constructor;
        try {
            constructor = toInstanciate.getConstructor(argumentTypes);
            return (T)constructor.newInstance(argument);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException exception) {
            throw new ReflectionException(
                            "Could not invoke constructor, please check if constructor exists and the parameters fit",
                            exception);
        }
    }

    /**
     * Invoke the default/non-arg constructor from {@code toInstanciate}.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate) {
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
     * @param argumentType
     *            - the parameter type of the constructor to use
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, Class<?> argumentType, Object argument) {
        return invokeConstructor(toInstanciate, new Class[] { argumentType }, new Object[] { argument });
    }

    /**
     * Invoke the int constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameters.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, int argument) {
        return invokeConstructor(toInstanciate, Integer.valueOf(argument));
    }

    /**
     * Invoke the Integer constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameters.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, Integer argument) {
        return invokeConstructor(toInstanciate, new Class[] { Integer.TYPE },
                        new Integer[] { argument });
    }

    /**
     * Invoke the boolean constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, boolean argument) {
        return invokeConstructor(toInstanciate, Boolean.valueOf(argument));
    }

    /**
     * Invoke the Boolean constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, Boolean argument) {
        return invokeConstructor(toInstanciate, new Class[] { Boolean.TYPE },
                        new Boolean[] { argument });
    }

    /**
     * Invoke the byte constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, byte argument) {
        return invokeConstructor(toInstanciate, Byte.valueOf(argument));
    }

    /**
     * Invoke the Byte constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, Byte argument) {
        return invokeConstructor(toInstanciate, new Class[] { Byte.TYPE },
                        new Byte[] { argument });
    }

    /**
     * Invoke the char constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, char argument) {
        return invokeConstructor(toInstanciate, Character.valueOf(argument));
    }

    /**
     * Invoke the Character constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, Character argument) {
        return invokeConstructor(toInstanciate, new Class[] { Character.TYPE },
                        new Character[] { argument });
    }

    /**
     * Invoke the double constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, double argument) {
        return invokeConstructor(toInstanciate, Double.valueOf(argument));
    }

    /**
     * Invoke the Double constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, Double argument) {
        return invokeConstructor(toInstanciate, new Class[] { Double.TYPE },
                        new Double[] { argument });
    }

    /**
     * Invoke the long constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, long argument) {
        return invokeConstructor(toInstanciate, Long.valueOf(argument));
    }

    /**
     * Invoke the Long constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, Long argument) {
        return invokeConstructor(toInstanciate, new Class[] { Long.TYPE },
                        new Long[] { argument });
    }

    /**
     * Invoke the short constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, short argument) {
        return invokeConstructor(toInstanciate, Short.valueOf(argument));
    }

    /**
     * Invoke the Short constructor from {@code toInstanciate} with
     * {@code parameterValues} as parameter.
     *
     * @param toInstanciate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstanciate
     */
    public static <T> T invokeConstructor(Class<T> toInstanciate, Short argument) {
        return invokeConstructor(toInstanciate, new Class[] { Short.TYPE },
                        new Short[] { argument });
    }

}
