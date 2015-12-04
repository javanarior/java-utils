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
package de.javanarior.utils.compare;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class SpeakingComparableAdapterTest {

    private static final Integer ONE = Integer.valueOf(1);
    private static final Integer TWO = Integer.valueOf(2);

    private SpeakingComparableInteger one;
    private SpeakingComparableInteger two;

    @BeforeClass
    public void setUpClass() {
        one = new SpeakingComparableInteger(1);
        two = new SpeakingComparableInteger(2);
    }

    public void testEqualsTo() {
        Assert.assertTrue(one.equalsTo(ONE));
        Assert.assertFalse(one.equalsTo(TWO));
        Assert.assertFalse(two.equalsTo(ONE));
        Assert.assertTrue(two.equalsTo(TWO));
    }

    public void testNotEqualsTo() {
        Assert.assertFalse(one.notEqualsTo(ONE));
        Assert.assertTrue(one.notEqualsTo(TWO));
        Assert.assertTrue(two.notEqualsTo(ONE));
        Assert.assertFalse(two.notEqualsTo(TWO));
    }

    public void testGreaterThan() {
        Assert.assertFalse(one.greaterThan(ONE));
        Assert.assertFalse(one.greaterThan(TWO));
        Assert.assertTrue(two.greaterThan(ONE));
        Assert.assertFalse(two.greaterThan(TWO));
    }

    public void testGreaterOrEqualsThan() {
        Assert.assertTrue(one.greaterOrEqualsThan(ONE));
        Assert.assertFalse(one.greaterOrEqualsThan(TWO));
        Assert.assertTrue(two.greaterOrEqualsThan(ONE));
        Assert.assertTrue(two.greaterOrEqualsThan(TWO));
    }

    public void testLessThan() {
        Assert.assertFalse(one.lessThan(ONE));
        Assert.assertTrue(one.lessThan(TWO));
        Assert.assertFalse(two.lessThan(ONE));
        Assert.assertFalse(two.lessThan(TWO));
    }

    public void testLessOrEqualsThan() {
        Assert.assertTrue(one.lessOrEqualsThan(ONE));
        Assert.assertTrue(one.lessOrEqualsThan(TWO));
        Assert.assertFalse(two.lessOrEqualsThan(ONE));
        Assert.assertTrue(two.lessOrEqualsThan(TWO));
    }

    // START SNIPPET: class
    private static class SpeakingComparableInteger extends SpeakingComparableAdapter<Integer> {

        private Integer value;

        SpeakingComparableInteger(int value) {
            this.value = Integer.valueOf(value);
        }

        @Override
        public int compareTo(Integer other) {
            return value.compareTo(other);
        }
    }
    // END SNIPPET: class
}
