package task4.logging;

import task4.TemperatureNotificationApplication;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Project logging configuration.
 *
 * @author Dmitrii_Mishenev
 */
public class LoggerConfiguration {
    public static final Logger LOGGER = Logger.getLogger(TemperatureNotificationApplication.class.getName());
    private static final Handler LOGGER_HANDLER = new ConsoleHandler();

    static {
        LOGGER_HANDLER.setLevel(Level.ALL);
        LOGGER.addHandler(LOGGER_HANDLER);
        LOGGER.setLevel(Level.ALL);
        LOGGER.setUseParentHandlers(false);
    }
}