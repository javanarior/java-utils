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
     * Invoke the constructor from {@code toInstantiate} with the parameter
     * specified by {@code parameterTypes} and provides {@code parameterValues}
     * as parameters.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argumentTypes
     *            - the parameter types of the constructor to use
     * @param argument
     *            - the values to pass to the constructor
     * @return a new instance of toInstantiate
     */
    @SuppressWarnings("unchecked")
    public static <T> T invokeConstructor(Class<T> toInstantiate, Class<?>[] argumentTypes, Object[] argument) {
        Constructor<?> constructor;
        try {
            constructor = toInstantiate.getConstructor(argumentTypes);
            return (T)constructor.newInstance(argument);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                        | IllegalArgumentException | InvocationTargetException exception) {
            throw new ReflectionException(
                            "Could not invoke constructor, please check if constructor exists and the parameters fit",
                            exception);
        }
    }

    /**
     * Invoke the default/non-arg constructor from {@code toInstantiate}.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate) {
        try {
            return toInstantiate.newInstance();
        } catch (InstantiationException | IllegalAccessException exception) {
            throw new ReflectionException("Could not invoke default constructor, please check if accessable", exception);
        }
    }

    /**
     * Invoke the constructor from {@code toInstantiate} with the parameter
     * specified by {@code parameterTypes} and provides {@code parameterValues}
     * as parameters.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argumentType
     *            - the parameter type of the constructor to use
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, Class<?> argumentType, Object argument) {
        return invokeConstructor(toInstantiate, new Class[] { argumentType }, new Object[] { argument });
    }

    /**
     * Invoke the int constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameters.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, int argument) {
        return invokeConstructor(toInstantiate, Integer.valueOf(argument));
    }

    /**
     * Invoke the Integer constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameters.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, Integer argument) {
        return invokeConstructor(toInstantiate, new Class[] { Integer.TYPE },
                        new Integer[] { argument });
    }

    /**
     * Invoke the boolean constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, boolean argument) {
        return invokeConstructor(toInstantiate, Boolean.valueOf(argument));
    }

    /**
     * Invoke the Boolean constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, Boolean argument) {
        return invokeConstructor(toInstantiate, new Class[] { Boolean.TYPE },
                        new Boolean[] { argument });
    }

    /**
     * Invoke the byte constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, byte argument) {
        return invokeConstructor(toInstantiate, Byte.valueOf(argument));
    }

    /**
     * Invoke the Byte constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, Byte argument) {
        return invokeConstructor(toInstantiate, new Class[] { Byte.TYPE },
                        new Byte[] { argument });
    }

    /**
     * Invoke the char constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, char argument) {
        return invokeConstructor(toInstantiate, Character.valueOf(argument));
    }

    /**
     * Invoke the Character constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, Character argument) {
        return invokeConstructor(toInstantiate, new Class[] { Character.TYPE },
                        new Character[] { argument });
    }

    /**
     * Invoke the double constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, double argument) {
        return invokeConstructor(toInstantiate, Double.valueOf(argument));
    }

    /**
     * Invoke the Double constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, Double argument) {
        return invokeConstructor(toInstantiate, new Class[] { Double.TYPE },
                        new Double[] { argument });
    }

    /**
     * Invoke the long constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, long argument) {
        return invokeConstructor(toInstantiate, Long.valueOf(argument));
    }

    /**
     * Invoke the Long constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, Long argument) {
        return invokeConstructor(toInstantiate, new Class[] { Long.TYPE },
                        new Long[] { argument });
    }

    /**
     * Invoke the short constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, short argument) {
        return invokeConstructor(toInstantiate, Short.valueOf(argument));
    }

    /**
     * Invoke the Short constructor from {@code toInstantiate} with
     * {@code parameterValues} as parameter.
     *
     * @param <T>
     *            - type of class to instantiate
     * @param toInstantiate
     *            - class object from which a new instance is to be created
     * @param argument
     *            - the value to pass to the constructor
     * @return a new instance of toInstantiate
     */
    public static <T> T invokeConstructor(Class<T> toInstantiate, Short argument) {
        return invokeConstructor(toInstantiate, new Class[] { Short.TYPE },
                        new Short[] { argument });
    }

}
