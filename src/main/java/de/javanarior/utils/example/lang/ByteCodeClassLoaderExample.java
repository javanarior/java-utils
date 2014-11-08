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
package de.javanarior.utils.example.lang;

import de.javanarior.utils.lang.ByteCodeClassLoader;
import de.javanarior.utils.lang.ByteCodeContainer;

/**
 *
 */
class ByteCodeClassLoaderExample {

    ByteCodeClassLoaderExample() throws Exception {
        String className = null;
        byte[] byteCode = null;
        // START SNIPPET: getClassLoader
        ByteCodeClassLoader classLoader = ByteCodeClassLoader.getClassLoader();
        // END SNIPPET: getClassLoader
        // START SNIPPET: loadClass
        Class<?> classInstance = classLoader.load(className, byteCode);
        classInstance.newInstance();
        // END SNIPPET: loadClass
        // START SNIPPET: loadClassWithContainer
        ByteCodeContainer generatedClass = AwesomeByteCodeGenerator.generate();
        classLoader.load(generatedClass);
        // END SNIPPET: loadClassWithContainer

    }

    private static final class AwesomeByteCodeGenerator {
        static ByteCodeContainer generate() {
            return null;
        }
    }

}
