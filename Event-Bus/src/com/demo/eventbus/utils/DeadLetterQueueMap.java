package com.demo.eventbus.utils;

import java.util.HashMap;
import java.util.Map;

public class DeadLetterQueueMap {
    static Map<String, String> deadQueueToOriginalMap = new HashMap<>();
    static Map<String, String> originalToDeadQueueMap = new HashMap<>();

    private DeadLetterQueueMap() {

    }

    public static String getOriginalTopicId(String deadQueueId) {
        return deadQueueToOriginalMap.get(deadQueueId);
    }

    public static String getDeadQueueId(String originalTopicId){
        return originalToDeadQueueMap.get(originalTopicId);
    }

    public static void putMapping(String deadQueueId,String originalTopicId) {
        deadQueueToOriginalMap.put(deadQueueId, originalTopicId);
        originalToDeadQueueMap.put(originalTopicId, deadQueueId);
    }
}
