package task4.http.serialization;

import task4.dto.SmsRequestDto;

/**
 * Creating valid HTTP POST request body for sending SMS via Routee.
 *
 * @author Dmitrii_Mishenev
 */
public class SmsRequestSerializer {
    private static final String SMS_REQUEST_BODY_TEMPLATE =
            "{ \"body\": \"%s\",\"to\" : \"%s\",\"from\": \"%s\"}";

    public String serializeBody(SmsRequestDto requestDto) {
        return String.format(SMS_REQUEST_BODY_TEMPLATE,
                requestDto.getBody(),
                requestDto.getReceiverPhoneNumber(),
                requestDto.getFrom());
    }
}