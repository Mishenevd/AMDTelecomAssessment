package task4.resource;

import task4.dto.SmsRequestDto;

/**
 * Resource for sending SMS on specified telephone number via Routee CPaaS.
 *
 * @author Dmitrii_Mishenev
 */
public interface RouteeSmsResource {

    /**
     * Send SMS via Routee platform.
     * @param smsRequestDto  valid request DTO for SMS sending
     */
    void sendSms(SmsRequestDto smsRequestDto);
}