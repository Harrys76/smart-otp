package vn.funtap.smartotp_plugin.otp;

import android.util.Log;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import vn.funtap.smartotp_plugin.util.Base32String;
import vn.funtap.smartotp_plugin.util.Utilities;

public class GenerateOtp {
    private static GenerateOtp INSTANCE;
    /** Counter for time-based OTPs (TOTP). */
    private final TotpCounter mTotpCounter;
    /** Default passcode timeout period (in seconds) */
    public static final int DEFAULT_INTERVAL = 60;
    private static final String LOCAL_TAG = "GAuthenticator.AcctDb";
    private static final int PIN_LENGTH = 6; // HOTP or TOTP
    private static final int REFLECTIVE_PIN_LENGTH = 9; // ROTP

    public static GenerateOtp getInstance(){
        if(INSTANCE == null){
            INSTANCE = new GenerateOtp();
        }
        return INSTANCE;
    }

    public GenerateOtp(){
        this(DEFAULT_INTERVAL);
    }
    public GenerateOtp(int interval) {
        mTotpCounter = new TotpCounter(interval);
    }

    public String generateOtp(String secret , int type){

        OtpType typeOtp = OtpType.getEnum(type);

        long otpState = 0;

        if (typeOtp == OtpType.TOTP) {
            // For time-based OTP, the state is derived from clock.
            otpState =
                    mTotpCounter.getValueAtTime(Utilities.millisToSeconds(System.currentTimeMillis()));
        }

        String result = null;
        try {
            result = computePin(secret, otpState, null);
        } catch (OtpSourceException e) {
            e.printStackTrace();
        }
        return result;
    }
    private String computePin(String secret, long otpState, byte[] challenge)
            throws OtpSourceException {
        if (secret == null || secret.length() == 0) {
            throw new OtpSourceException("Null or empty secret");
        }

        try {
            PasscodeGenerator.Signer signer = getSigningOracle(secret);
            PasscodeGenerator pcg = new PasscodeGenerator(signer,
                    (challenge == null) ? PIN_LENGTH : REFLECTIVE_PIN_LENGTH);

            return (challenge == null) ?
                    pcg.generateResponseCode(otpState) :
                    pcg.generateResponseCode(otpState, challenge);
        } catch (GeneralSecurityException e) {
            throw new OtpSourceException("Crypto failure", e);
        }
    }
    public static PasscodeGenerator.Signer getSigningOracle(String secret) {
        try {
            byte[] keyBytes = decodeKey(secret);
            final Mac mac = Mac.getInstance("HMACSHA1");
            mac.init(new SecretKeySpec(keyBytes, ""));

            // Create a signer object out of the standard Java MAC implementation.
            return new PasscodeGenerator.Signer() {
                @Override
                public byte[] sign(byte[] data) {
                    return mac.doFinal(data);
                }
            };
        } catch (Base32String.DecodingException error) {
            Log.e(LOCAL_TAG, error.getMessage());
        } catch (NoSuchAlgorithmException error) {
            Log.e(LOCAL_TAG, error.getMessage());
        } catch (InvalidKeyException error) {
            Log.e(LOCAL_TAG, error.getMessage());
        }

        return null;
    }
    private static byte[] decodeKey(String secret) throws Base32String.DecodingException {
        return Base32String.decode(secret);
    }

    public enum OtpType {  // must be the same as in res/values/strings.xml:type
        TOTP (0),  // time based
        HOTP (1);  // counter based

        public final Integer value;  // value as stored in SQLite database
        OtpType(Integer value) {
            this.value = value;
        }

        public static OtpType getEnum(Integer i) {
            for (OtpType type : OtpType.values()) {
                if (type.value.equals(i)) {
                    return type;
                }
            }

            return null;
        }
    }
}
