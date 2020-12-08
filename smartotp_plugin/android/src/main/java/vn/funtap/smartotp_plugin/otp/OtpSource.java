package vn.funtap.smartotp_plugin.otp;

import java.util.List;

public interface OtpSource {

    /**
     * Gets the counter for generating or verifying TOTP codes.
     */
    TotpCounter getTotpCounter();

    /**
     * Gets the clock for generating or verifying TOTP codes.
     */
    TotpClock getTotpClock();
}

