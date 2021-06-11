package task4.http.serialization;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import task4.dto.SmsRequestDto;

/**
 * Unit test for {@link SmsRequestSerializer}
 *
 * @author Dmitrii_Mishenev
 */
class SmsRequestSerializerTest {
    private final SmsRequestSerializer smsRequestSerializer = new SmsRequestSerializer();

    @Test
    void notEmptyDto_serializeBody_shouldSerialize() {
        SmsRequestDto dto = SmsRequestDto
                .builder()
                .accessToken("123")
                .body("body")
                .from("Dmitrii M")
                .receiverPhoneNumber("+79992292751")
                .build();

        String response = smsRequestSerializer.serializeBody(dto);

        assertEquals("{ \"body\": \"body\",\"to\" : \"+79992292751\",\"from\": \"Dmitrii M\"}", response);
    }
}