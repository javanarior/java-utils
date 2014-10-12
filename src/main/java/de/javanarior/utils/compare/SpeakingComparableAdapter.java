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

/**
 * Abstract implementation of {@code SpeakingComparable} which rely on
 * {@code Comparable#compareTo(Object)}.
 *
 * @param <T>
 *            the type of the object to compare
 */
public abstract class SpeakingComparableAdapter<T> implements SpeakingComparable<T> {

    @Override
    public abstract int compareTo(T other);

    @Override
    public boolean equalsTo(T other) {
        return compareTo(other) == 0;
    }

    @Override
    public boolean notEqualsTo(T other) {
        return !equalsTo(other);
    }

    @Override
    public boolean greaterThan(T other) {
        return compareTo(other) > 0;
    }

    @Override
    public boolean greaterOrEqualsThan(T other) {
        return greaterThan(other) || equalsTo(other);
    }

    @Override
    public boolean lessThan(T other) {
        return !greaterOrEqualsThan(other);
    }

    @Override
    public boolean lessOrEqualsThan(T other) {
        return !greaterThan(other);
    }

}
