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
package de.javanarior.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

import de.javanarior.utils.compare.SpeakingComparable;

@Test
public class SpeakingTest {

    @Test(expectedExceptions = IllegalArgumentException.class,
          expectedExceptionsMessageRegExp = "^Argument 'comparable' must not be null$")
    public void testComparableWithNullArgument() {
        Speaking.comparable(null);
    }

    public void testComparable() {
        // START SNIPPET: factory-call
        SpeakingComparable<Integer> speakingComparable = Speaking.comparable(Integer.valueOf(1));
        // END SNIPPET: factory-call
        Assert.assertTrue(speakingComparable.equalsTo(1));
        Assert.assertFalse(speakingComparable.equalsTo(2));
    }

}
