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

/**
 * Indicates a error when working with Reflections.
 */
public class ReflectionException extends RuntimeException {

    private static final long serialVersionUID = 7561200076222225501L;

    /**
     * Indicates a error when working with Reflections.
     */
    public ReflectionException() {
        super();
    }

    /**
     * Indicates a error when working with Reflections.
     *
     * @param message
     *            - some hints of the cause or tips to handle
     */
    public ReflectionException(String message) {
        super(message);
    }

    /**
     * Indicates a error when working with Reflections.
     *
     * @param cause
     *            - the root cause of this error
     */
    public ReflectionException(Throwable cause) {
        super(cause);
    }

    /**
     * Indicates a error when working with Reflections.
     *
     * @param message
     *            - some hints of the cause or tips to handle
     * @param cause
     *            - the root cause of this error
     */
    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Indicates a error when working with Reflections.
     *
     * @param message
     *            - some hints of the cause or tips to handle
     * @param cause
     *            - the root cause of this error
     * @param enableSuppression
     *            - enable suppression
     * @param writableStackTrace
     *            - writable stack trace
     */
    public ReflectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
