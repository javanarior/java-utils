package de.javanarior.utils.concurrent.threadlocal;

/**
 * Is used by {@link ContextCopiedThreadFactory} to copy
 * ThreadLocal variables to a new Thread.
 */
public interface ContextCopier {

    /**
     * Writes the saved values in the new Thread.
     */
    void apply();

    /**
     * Saves the values from the origin Thread.
     */
    void copy();

}
