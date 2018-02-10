package de.javanarior.utils.concurrent.threadlocal;

/**
 * Implementations of this Interface must provide a new instance every time
 * {@link ContextCopierFactory}{@link #get()} is called.
 */
public interface ContextCopierFactory {

    /**
     * Creates a new fresh {@link ContextCopier}.
     *
     * @return a new instance of ContextCopier.
     */
    ContextCopier get();
}
