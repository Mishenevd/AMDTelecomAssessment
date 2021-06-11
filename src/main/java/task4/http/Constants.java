package task4.http;

/**
 * Common application constants.
 * For reducing duplicates in code.
 *
 * @author Dmitrii_Mishenev
 */
public enum Constants {
    CONTENT_TYPE("Content-Type"),
    APPLICATION_JSON("application/json"),
    FORM_URLENCODED("application/x-www-form-urlencoded"),
    AUTHORIZATION("Authorization"),
    BEARER_TOKEN_TEMPLATE("Bearer %s"),
    BASIC_AUTH_TEMPLATE("Basic %s");

    private final String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}