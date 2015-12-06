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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class ByteCodeClassLoaderTest {

    private static final String CLASS_NAME = "de.javanarior.utils.lang.TestMe";
    private ByteCodeClassLoader classLoader;

    @BeforeMethod
    public void setUp() {
        classLoader = ByteCodeClassLoader.getClassLoader();
    }

    @AfterMethod
    public void tearDown() {
        classLoader = null;
    }

    public void testLoad() throws Exception {
        Class<?> clazz = classLoader.load(CLASS_NAME, readByteCode());
        Assert.assertNotNull(clazz);
    }

    public void testLoadWithContainer() throws Exception {
        ByteCodeContainer container = new ByteCodeContainer(CLASS_NAME, readByteCode());
        Class<?> clazz = classLoader.load(container);
        Assert.assertNotNull(clazz);
    }

    public void testLoadAndRetrieve() throws Exception {
        Class<?> loaded = classLoader.load(CLASS_NAME, readByteCode());
        Class<?> retrieved = classLoader.loadClass(CLASS_NAME);
        Assert.assertNotNull(loaded);
        Assert.assertNotNull(retrieved);
        Assert.assertEquals(loaded, retrieved);
        Assert.assertSame(loaded, retrieved);
    }

    public void testLoadClassTwice() throws Exception {
        Class<?> first = classLoader.load(CLASS_NAME, readByteCode());
        Class<?> second = classLoader.load(CLASS_NAME, readByteCode());
        Assert.assertNotNull(first);
        Assert.assertNotNull(second);
        Assert.assertEquals(first, second);
        Assert.assertSame(first, second);
    }

    public void testLoadSecondClassWithNewReference() throws Exception {
        Class<?> first = classLoader.load(CLASS_NAME, readByteCode());
        classLoader = ByteCodeClassLoader.getClassLoader();
        Class<?> second = classLoader.load(CLASS_NAME, readByteCode());
        Assert.assertNotNull(first);
        Assert.assertNotNull(second);
        Assert.assertEquals(first, second);
        Assert.assertSame(first, second);
    }

    private byte[] readByteCode() throws IOException {
        Path file = Paths.get("src/test/resources/de.javanarior.utils.lang.TestMe.class");
        return Files.readAllBytes(file);
    }

}
