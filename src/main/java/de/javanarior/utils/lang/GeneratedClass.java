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
 * Container for a generated class. This container contains the binary code of
 * the class and the class name in the binary format. Both are necessary to load
 * the class into the JVM.
 */
public class GeneratedClass {

    private final String className;
    private final byte[] byteCode;

    /**
     * Create a new GeneratedClass instance.
     *
     * @param className
     *            - class name in binary format
     * @param byteCode
     *            - of the class
     */
    public GeneratedClass(String className, byte[] byteCode) {
        this.className = className;
        this.byteCode = byteCode;
    }

    public String getClassName() {
        return className;
    }

    public byte[] getByteCode() {
        return byteCode;
    }

}
