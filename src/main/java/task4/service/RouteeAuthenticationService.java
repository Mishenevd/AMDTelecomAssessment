package task4.service;

/**
 * Service for authentication in Routee CPaaS.
 *
 * @author Dmitrii_Mishenev
 */
public interface RouteeAuthenticationService {

    /**
     * Retrieve authentication token.
     * @param appId routee application id.
     * @param appSecret routee application secret.
     * @return bearer access token to routee.
     */
    String authenticate(String appId, String appSecret);
}