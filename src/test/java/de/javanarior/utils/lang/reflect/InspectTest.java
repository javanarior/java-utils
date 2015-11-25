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

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class InspectTest {

    public void testReadFields() {
        List<?> fields = Inspect.readFields(TestObject.class);
        Assert.assertEquals(fields.size(), 4);
    }

    private static final class TestObject {
        private String field1;
        protected String field2;
        String field3;
        public String field4;
    }
}
