package de.javanarior.utils.concurrent;

public abstract class TryCatchRunnable implements Runnable {

    public abstract void runInTryBlock() throws InterruptedException;

    @Override
    public void run() {
        try {
            runInTryBlock();
        } catch (InterruptedException exception) {
            // TODO how to handle exception properly
            exception.printStackTrace();
        }
    }


}
