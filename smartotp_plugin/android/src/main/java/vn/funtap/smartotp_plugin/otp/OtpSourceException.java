package vn.funtap.smartotp_plugin.otp;

/** Indicates that {@link OtpSource} failed to performed the requested operation. */
public class OtpSourceException extends Exception {
    public OtpSourceException(String message) {
        super(message);
    }

    public OtpSourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
