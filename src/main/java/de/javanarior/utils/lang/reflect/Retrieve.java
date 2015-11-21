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

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Retrieve values from Java Elements e.g. {@link Annotation}s.
 */
public final class Retrieve {

    private Retrieve() {
        /* Utility class */
    }

    /**
     * Retrieve the value of an {@linkplain Annotation} attribute/method on a
     * Class.
     * To be more precise returns the value of {@code attributeName} from
     * {@code annationClass} on {@code annotatedClass}.
     * In case of error a {@linkplain ReflectionException} is thrown.
     *
     * @param annotatedClass
     *            - the class with the Annotation
     * @param annotationClass
     *            - the class of the Annotation
     * @param <T>  - a annotation class
     * @param attributeName
     *            - name of the attribute
     * @return value of the annotation attribute
     */
    public static <T extends Annotation> Object annotationValueOnClass(Class<?> annotatedClass,
                    Class<T> annotationClass, String attributeName) {
        try {
            T annotation = annotatedClass.getAnnotation(annotationClass);
            if (annotation == null) {
                throw new ReflectionException("Annotation '" + annotationClass + "' not found on Class '"
                                + annotatedClass.getCanonicalName() + "'");
            }
            return invokeAnnotation(attributeName, annotation);
        } catch (SecurityException exception) {
            throw new ReflectionException(exception);
        }
    }

    /**
     * Retrieve the value of an {@linkplain Annotation} on a Class. The
     * Attribute/Method
     * 'value' is assumed as default.
     * To be more precise returns the value of {@code value} from
     * {@code annationClass} on {@code annotatedClass}.
     * In case of error a {@linkplain ReflectionException} is thrown.
     *
     * @param annotatedClass
     *            - the class with the Annotation
     * @param annotationClass
     *            - the class of the Annotation
     * @param <T>  - a annotation class
     * @return value of the annotation attribute
     */
    public static <T extends Annotation> Object annotationValueOnClass(Class<?> annotatedClass, Class<T> annotationClass) {
        return annotationValueOnClass(annotatedClass, annotationClass, "value");
    }


    /**
     * Retrieve the value of an {@linkplain Annotation} on a Method.
     * To be more precise returns the value of {@code attributeName} from
     * {@code annationClass} on {@code methodWithAnnotation}.
     * In case of error a {@linkplain ReflectionException} is thrown.
     *
     * @param annotationClass
     *            - the class of the Annotation
     * @param <T>  - a annotation class
     * @param methodWithAnnotation
     *            - method with annotation
     * @param attributeName
     *            - name of the attribute
     * @return value of the annotation attribute
     */
    public static <T extends Annotation> Object annotationValueOnMethod(Class<T> annotationClass,
                    Method methodWithAnnotation, String attributeName) {
        try {
            T annotation = methodWithAnnotation.getAnnotation(annotationClass);
            if (annotation == null) {
                throw new ReflectionException("Annotation '" + annotationClass + "' not found on Method '"
                                + methodWithAnnotation + "'");
            }
            return invokeAnnotation(attributeName, annotation);
        } catch (SecurityException exception) {
            throw new ReflectionException(exception);
        }
    }

    /**
     * Retrieve the value of an {@linkplain Annotation} on a Method.
     * To be more precise returns the value of {@code attributeName} of
     * {@code annotationClass} from {@code methodName} of
     * {@code classWithMethod}.
     * In case of error a {@linkplain ReflectionException} is thrown.
     *
     * @param classWithMethod
     *            - class with the method
     * @param methodName
     *            - name of method with the annotation
     * @param annotationClass
     *            - the class of the Annotation
     * @param <T>  - a annotation class
     * @param attributeName
     *            - name of the attribute
     * @return value of the annotation attribute
     */
    public static <T extends Annotation> Object annotationValueOnMethod(Class<?> classWithMethod, String methodName,
                    Class<T> annotationClass, String attributeName) {
        try {
            return annotationValueOnMethod(annotationClass, getMethod(classWithMethod, methodName), attributeName);
        } catch (SecurityException exception) {
            throw new ReflectionException(exception);
        }
    }

    /**
     * Retrieve the value of an {@linkplain Annotation} on a Method. The
     * Attribute/Method 'value' is assumed as default.
     * To be more precise returns the value of {@code value} from
     * {@code annationClass} on {@code method}.
     * In case of error a {@linkplain ReflectionException} is thrown.
     *
     * @param annotationClass
     *            - the class of the Annotation
     * @param <T>  - a annotation class
     * @param method
     *            - the method with the Annotation
     * @return value of the annotation attribute
     */
    public static <T extends Annotation> Object annotationValueOnMethod(Class<T> annotationClass, Method method) {
        return annotationValueOnMethod(annotationClass, method, "value");
    }

    /**
     * Retrieve the value of an {@linkplain Annotation} on a Method. The
     * Attribute/Method 'value' is assumed as default.
     * To be more precise returns the value of {@code value} from
     * {@code annationClass} of {@code methodName} of {@code annotatedClass}.
     * In case of error a {@linkplain ReflectionException} is thrown.
     *
     * @param classWithMethod
     *            - class with method
     * @param methodName
     *            - name of method with annotation
     * @param annotationClass
     *            - the class of the Annotation
     * @param <T>  - a annotation class
     * @return value of the annotation attribute
     */
    public static <T extends Annotation> Object annotationValueOnMethod(Class<?> classWithMethod, String methodName,
                    Class<T> annotationClass) {
        try {
            return annotationValueOnMethod(annotationClass, getMethod(classWithMethod, methodName));
        } catch (SecurityException exception) {
            throw new ReflectionException(exception);
        }
    }



    private static Method getMethod(Class<?> annotatedClass, String methodName) {
        try {
            return annotatedClass.getMethod(methodName);
        } catch (NoSuchMethodException exception) {
            throw new ReflectionException("Method name '" + methodName + "' not found in Class '"
                            + annotatedClass.getCanonicalName() + "'", exception);
        }
    }

    private static <T extends Annotation> Object invokeAnnotation(String attributeName, T annotation) {
        try {
            Method method = annotation.annotationType().getMethod(attributeName);
            return method.invoke(annotation);
        } catch (NoSuchMethodException exception) {
            throw new ReflectionException("Attribute '" + attributeName + "' not found on '"
                            + annotation.annotationType().getCanonicalName() + "'", exception);
        } catch (SecurityException | IllegalAccessException | InvocationTargetException exception) {
            throw new ReflectionException(exception);
        }
    }

}
