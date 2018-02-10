package de.javanarior.utils.concurrent.threadlocal;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * A ThreadFactory which allows to copy ThreadLocal variables to the new Thread.
 * To copy the ThreadLocal values a {@link ContextCopier} is used, which has to
 * be provided by a {@link ContextCopierFactory}. Implementations of
 * {@link ContextCopier} and {@link ContextCopierFactory} must also be provided.
 */
public class ContextCopiedThreadFactory implements ThreadFactory {

    private final ThreadFactory delegate;
    private final ContextCopierFactory contextCopierFactory;

    /**
     * Creates a new ThreadFactory with a underlying @{code {@link ThreadFactory}
     * and {@link ContextCopier}.
     *
     * @param threadFactory
     *            - used to create threads
     * @param contextCopier
     *            - used to copy ThreadLocal variables
     */
    public ContextCopiedThreadFactory(ThreadFactory threadFactory, ContextCopierFactory contextCopier) {
        this.delegate = threadFactory;
        this.contextCopierFactory = contextCopier;
    }

    /**
     * Creates a new ThreadFactory with a {@link Executors#defaultThreadFactory()}
     * and {@link ContextCopier}.
     *
     * @param contextCopier
     *            - used to copy ThreadLocal variables
     */
    public ContextCopiedThreadFactory(ContextCopierFactory contextCopier) {
        this(Executors.defaultThreadFactory(), contextCopier);
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        final ContextCopier contextCopier = contextCopierFactory.get();
        contextCopier.copy();
        return delegate.newThread(new Runnable() {

            @Override
            public void run() {
                contextCopier.apply();
                runnable.run();
            }
        });
    }

}
