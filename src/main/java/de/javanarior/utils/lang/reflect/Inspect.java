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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Inspect {

    public static List<?> readFields(Class<?> clazz) {
//        List<?> fields = new ArrayList<>();
//        clazz.getDeclaredFields();
//        fields.addAll(clazz.getDeclaredFields());
        return Arrays.asList(clazz.getDeclaredFields());
    }

    public static List<Method> findMethods(Method[] methods, String methodName) {
        List<Method> matchingMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                matchingMethods.add(method);
            }
        }
        return matchingMethods;
    }

}
