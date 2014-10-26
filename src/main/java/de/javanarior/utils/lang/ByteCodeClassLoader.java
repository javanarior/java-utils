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
package de.javanarior.utils.lang;

import java.util.Hashtable;
import java.util.Map;

/**
 * Class loader to load byte code directly from byte array. This class loader
 * can be used
 * to load classes from Java Compiler API which are compiled in memory or from
 * other byte
 * generating frameworks like asm or cglib.
 * The ClassLoader follows the 'parent-first' delegation model .
 */
public class ByteCodeClassLoader extends ClassLoader {

    private static final Map<String, Class<?>> CLASSES = new Hashtable<>();

    /**
     * Create a new ByteCodeClassLoader instance.
     *
     * @param parent
     *            - parent class loader
     */
    public ByteCodeClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = CLASSES.get(name);
        if (clazz != null) {
            return clazz;
        }
        return super.findClass(name);
    }

    /**
     * Load class with name {@code binaryName} with byte code {@code byteCode}.
     * If the class was loaded previously, the class is loaded from static
     * cache.
     *
     * @param binaryName
     *            - The Class name in the Binary name format, as expected from
     *            {@link ClassLoader}
     * @param byteCode
     *            - byte code to load
     * @return {@code Class} object
     */
    public Class<?> load(String binaryName, byte[] byteCode) {
        Class<?> loadedClass = findLoadedClass(binaryName);
        if (loadedClass != null) {
            return loadedClass;
        }
        try {
            return loadClass(binaryName);
        } catch (ClassNotFoundException exception) {
            Class<?> defineClass = defineClass(binaryName, byteCode, 0, byteCode.length);
            resolveClass(defineClass);
            CLASSES.put(binaryName, defineClass);
            return defineClass;
        }
    }

}
