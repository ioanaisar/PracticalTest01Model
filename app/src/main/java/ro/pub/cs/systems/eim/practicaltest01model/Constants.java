package ro.pub.cs.systems.eim.practicaltest01model;

public class Constants {

    public static final String LEFT_TEXT = "leftText";
    public static final String RIGHT_TEXT = "rightText";

    public static final Integer RequestCode = 1;

    public static final String PROCESSING_THREAD_TAG =  "[Processing Thread]";
    public static final String BROADCAST_RECEIVER_EXTRA = "ro.pub.cs.systems.eim.practicaltest01.broadcastreceiverextra";
    public static final Object SERVICE_STARTED = 1;
    public static final Object SERVICE_STOPPED = 0;
    public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.arithmeticmean",
            "ro.pub.cs.systems.eim.practicaltest01.geometricmean"
    };
}
