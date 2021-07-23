package com.soho.queue;

public class UUIDGenerator {
	
	   /**
     * Generate uuid long.
     *
     * @return the long
     */
    public static long generateUUID() {
        return IdWorker.getInstance().nextId();
    }

    /**
     * Init.
     *
     * @param serverNode the server node id
     */
    public static void init(Long serverNode) {
        IdWorker.init(serverNode);
    }
}
