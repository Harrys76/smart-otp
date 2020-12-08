package vn.funtap.smartotp_plugin.util.time;

/** Provides the current value of "now" to improve testability. */
public interface Clock {
    /** Gets the current time in milliseconds. */
    long nowMillis();
}
