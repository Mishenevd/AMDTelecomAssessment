package task4.service;

import task4.exception.InvalidAuthCredentialsException;
import task4.http.util.CredentialsBase64Encoder;
import task4.resource.RouteeAuthResource;
import task4.resource.RouteeResourceImpl;

/**
 * Service for authentication in Routee CPaaS.
 *
 * @author Dmitrii_Mishenev
 */
public class RouteeAuthenticationServiceImpl implements RouteeAuthenticationService {
    private final RouteeAuthResource routeeResource;
    private final CredentialsBase64Encoder encoder;

    public RouteeAuthenticationServiceImpl(RouteeAuthResource routeeResource, CredentialsBase64Encoder encoder) {
        this.routeeResource = routeeResource;
        this.encoder = encoder;
    }

    public RouteeAuthenticationServiceImpl() {
        this.encoder = new CredentialsBase64Encoder();
        routeeResource = new RouteeResourceImpl();
    }

    /**
     * Retrieve authentication token.
     * @param appId routee application id.
     * @param appSecret routee application secret.
     * @return bearer access token to routee.
     */
    @Override
    public String authenticate(String appId, String appSecret) {
        if (isEmpty(appId) || isEmpty(appSecret)) {
            throw new InvalidAuthCredentialsException(appId, appSecret);
        }

        String encodedToken = encoder.encodeCredentials(appId, appSecret);

        return routeeResource.authenticate(encodedToken);
    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
}