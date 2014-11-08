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

/**
 * Classloader to load byte code directly from byte array. This classloader
 * can be used
 * to load classes from Java Compiler API which are compiled in memory or from
 * other byte generating frameworks like asm.
 * The ClassLoader follows the 'parent-first' delegation model .
 */
public final class ByteCodeClassLoader extends ClassLoader {

    private static final ByteCodeClassLoader INSTANCE = new ByteCodeClassLoader(getSystemClassLoader());

    /**
     * Create a new ByteCodeClassLoader instance.
     *
     * @param parent
     *            - parent classloader
     */
    private ByteCodeClassLoader(ClassLoader parent) {
        super(parent);
    }

    public static ByteCodeClassLoader getClassLoader() {
        return INSTANCE;
    }

    /**
     * Load class with name {@code binaryName} with byte code {@code byteCode}.
     *
     * @param binaryName
     *            - The Class name in the binary name format, as expected from
     *            {@link ClassLoader}
     * @param byteCode
     *            - byteCode to load
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
            return defineClass;
        }
    }

    /**
     * Load the class from a {@link GeneratedClass} container.
     *
     * @param clazz
     *            - to load
     * @return class object
     */
    public Class<?> load(GeneratedClass clazz) {
        return load(clazz.getClassName(), clazz.getByteCode());
    }

}
