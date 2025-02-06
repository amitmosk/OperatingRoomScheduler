package com.ServiceLayer;

import static com.BusinessLayer.Constants.DAEMON_INTERVALS;

public class QueueProcessor {
    public QueueProcessor() {
        Thread thread = new Thread(new QueueProcessorThread());
        thread.start();
    }

    private static class QueueProcessorThread implements Runnable {
        @Override
        public void run() {
            OperatingRoomScheduler scheduler = OperatingRoomScheduler.getInstance();
            while (true) {
                try {
                    Thread.sleep(DAEMON_INTERVALS);
                    scheduler.processQueue();
                } catch (Exception e) {
                    break;
                }
            }
        }
    }

}
