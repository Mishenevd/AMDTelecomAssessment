package task4.resource;

import static task4.http.Constants.APPLICATION_JSON;
import static task4.http.Constants.AUTHORIZATION;
import static task4.http.Constants.BASIC_AUTH_TEMPLATE;
import static task4.http.Constants.BEARER_TOKEN_TEMPLATE;
import static task4.http.Constants.CONTENT_TYPE;
import static task4.http.Constants.FORM_URLENCODED;

import java.net.URI;
import java.net.http.HttpRequest;
import java.util.function.Function;
import task4.dto.SmsRequestDto;
import task4.http.DefaultHttpBadResponseHandler;
import task4.http.HttpFacade;
import task4.http.HttpFacadeImpl;
import task4.http.serialization.RouteeTokenParser;
import task4.http.serialization.SmsRequestSerializer;
import task4.service.TemperatureSmsNotificationServiceImpl;

/**
 * High level abstraction for reducing complexity in HTTP communication.
 *
 * @author Dmitrii_Mishenev
 */
public class RouteeResourceImpl implements RouteeSmsResource, RouteeAuthResource {
    private static volatile RouteeResourceImpl SINGLETON;

    private static final String SMS_AUTH_BODY = "grant_type=client_credentials";
    private static final String SMS_AUTH_URL = "https://auth.routee.net/oauth/token";
    private static final String SMS_REQUEST_URL = "https://connect.routee.net/sms";

    private final HttpFacade<String> smsFacade;
    private final HttpFacade<String> authenticationFacade;
    private final SmsRequestSerializer smsRequestSerializer;

    public static RouteeResourceImpl getInstance() {
        if (SINGLETON != null) {
            return SINGLETON;
        }
        synchronized (TemperatureSmsNotificationServiceImpl.class) {
            if (SINGLETON == null) {
                SINGLETON = new RouteeResourceImpl();
            }
            return SINGLETON;
        }
    }

    private RouteeResourceImpl(HttpFacade<String> smsFacade,
                              HttpFacade<String> authenticationFacade,
                              SmsRequestSerializer smsRequestSerializer) {
        this.smsFacade = smsFacade;
        this.authenticationFacade = authenticationFacade;
        this.smsRequestSerializer = smsRequestSerializer;
    }

    private RouteeResourceImpl() {
        smsFacade = new HttpFacadeImpl<>(
                Function.identity(),
                new DefaultHttpBadResponseHandler());
        authenticationFacade = new HttpFacadeImpl<>(
                new RouteeTokenParser(),
                new DefaultHttpBadResponseHandler());
        smsRequestSerializer = new SmsRequestSerializer();
    }

    @Override
    public String authenticate(String base64EncodedToken) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(SMS_AUTH_URL))
                .POST(HttpRequest.BodyPublishers.ofString(SMS_AUTH_BODY))
                .header(AUTHORIZATION.getValue(), String.format(BASIC_AUTH_TEMPLATE.getValue(), base64EncodedToken))
                .header(CONTENT_TYPE.getValue(), FORM_URLENCODED.getValue())
                .build();

        return authenticationFacade.exchange(request);
    }

    @Override
    public void sendSms(SmsRequestDto smsRequestDto) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(SMS_REQUEST_URL))
                .POST(HttpRequest.BodyPublishers
                        .ofString(smsRequestSerializer.serializeBody(smsRequestDto)))
                .header(AUTHORIZATION.getValue(), String.format(BEARER_TOKEN_TEMPLATE.getValue(), smsRequestDto.getAccessToken()))
                .header(CONTENT_TYPE.getValue(), APPLICATION_JSON.getValue())
                .build();

        smsFacade.exchange(request);
    }
}