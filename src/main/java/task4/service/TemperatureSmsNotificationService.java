package task4.service;

/**
 * Service for checking actual temperature and sending SMS with.
 *
 * @author Dmitrii_Mishenev
 */
public interface TemperatureSmsNotificationService {

    /**
     * Check actual temperature and SMS notification.
     */
    void notifyBySms();
}