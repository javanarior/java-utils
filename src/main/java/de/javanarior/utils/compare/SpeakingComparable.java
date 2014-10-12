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
 * A more speaking Comparable interface.
 * Usually the methods in this interface rely on
 * {@code Comparable#compareTo(Object)}.
 * <p>
 * As mentioned in {@link Comparable} the {@code compareTo} and {@code equals}
 * does not have strictly to be consistent (
 * {@code (x.compareTo(y)==0) == (x.equals(y))}), even it's recommend and should
 * be noted. For this reason the equals method here is called {@code equalsTo}.
 *
 * @param <T>
 *            the type of the object to compare
 */
public interface SpeakingComparable<T> extends Comparable<T> {

    /**
     * Returns true if {@code this} is equals {@code other}.
     *
     * @param other
     *            to compare
     * @return true if this is equals other, false otherwise
     */
    boolean equalsTo(T other);

    /**
     * Returns true if {@code this} is <i>not</i> equals {@code other}.
     *
     * @param other
     *            to compare
     * @return true if this is <i>not</i> equals other, false otherwise
     */
    boolean notEqualsTo(T other);

    /**
     * Returns true if {@code this} is greater than {@code other}.
     *
     * @param other
     *            to compare
     * @return true if this is greater than other, false otherwise
     */
    boolean greaterThan(T other);

    /**
     * Returns true if {@code this} is greater or equals than {@code other}.
     *
     * @param other
     *            to compare
     * @return true if this is greater or equals than other, false otherwise
     */
    boolean greaterOrEqualsThan(T other);

    /**
     * Returns true if {@code this} is less than {@code other}.
     *
     * @param other
     *            to compare
     * @return true if this is less than other, false otherwise
     */
    boolean lessThan(T other);

    /**
     * Returns true if {@code this} is less or equals than {@code other}.
     *
     * @param other
     *            to compare
     * @return true if this is less or equals than other, false otherwise
     */
    boolean lessOrEqualsThan(T other);

}
