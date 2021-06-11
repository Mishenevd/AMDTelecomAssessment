package task4.dto;

/**
 * Dto represents request for sending SMS via Routee CPaaS.
 *
 * @author Dmitrii_Mishenev
 */
public class SmsRequestDto {

    /**
     * Receiver's phone number in  E.164 number formatting.
     */
    private String receiverPhoneNumber;

    /**
     * SMS content.
     */
    private String body;

    /**
     * Sender's name or phone number.
     */
    private String from;

    /**
     * Routee's Access token.
     */
    private String accessToken;

    public SmsRequestDto(String receiverPhoneNumber, String body, String from, String accessToken) {
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.body = body;
        this.from = from;
        this.accessToken = accessToken;
    }

    public SmsRequestDto() {

    }

    public static SmsRequestDtoBuilder builder() {
        return new SmsRequestDtoBuilder();
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public String getBody() {
        return body;
    }

    public String getFrom() {
        return from;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public static class SmsRequestDtoBuilder {
        private String receiverPhoneNumber;
        private String body;
        private String from;
        private String accessToken;

        SmsRequestDtoBuilder() {
        }

        public SmsRequestDtoBuilder receiverPhoneNumber(String receiverPhoneNumber) {
            this.receiverPhoneNumber = receiverPhoneNumber;
            return this;
        }

        public SmsRequestDtoBuilder body(String body) {
            this.body = body;
            return this;
        }

        public SmsRequestDtoBuilder from(String from) {
            this.from = from;
            return this;
        }

        public SmsRequestDtoBuilder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public SmsRequestDto build() {
            return new SmsRequestDto(receiverPhoneNumber, body, from, accessToken);
        }

        public String toString() {
            return "SmsRequestDto.SmsRequestDtoBuilder(receiverPhoneNumber=" + this.receiverPhoneNumber + ", body=" + this.body + ", from=" + this.from + ", accessToken=" + this.accessToken + ")";
        }
    }
}