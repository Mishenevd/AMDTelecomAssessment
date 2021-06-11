package task4.resource;

/**
 * Resource for Authenticate at routee's authorization server.
 *
 * @author Dmitrii_Mishenev
 */
public interface RouteeAuthResource {

    /**
     * Authenticate at routee's authorization server.
     * @param base64EncodedToken base-64 encoded token in "applicationid:applicationsecret" format.
     * @return access token for 1 hour.
     */
    String authenticate(String base64EncodedToken);
}