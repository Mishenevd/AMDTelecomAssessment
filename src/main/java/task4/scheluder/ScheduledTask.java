package task4.scheluder;

import static task4.logging.LoggerConfiguration.LOGGER;

import java.util.logging.Level;

/**
 * Task to be scheduled in {@link ScheduledTaskRunner}
 *
 * @author Dmitrii_Mishenev
 */
public class ScheduledTask implements Runnable{
    private static final String ERROR_MSG = "Error during task invocation. Cause: ";

    private final Runnable task;
    private final Runnable terminationCallback;

    /**
     * ScheduledTask constructor.
     * @param task task to execute.
     * @param terminationCallback will be executed on success or fail task invocation.
     */
    public ScheduledTask(Runnable task, Runnable terminationCallback) {
        this.task = task;
        this.terminationCallback = terminationCallback;
    }

    @Override
    public void run() {
        try {
            task.run();
        } catch (Exception exception) {
            LOGGER.log(Level.WARNING, ERROR_MSG + exception.getCause(), exception);
        }
        finally {
            terminationCallback.run();
        }
    }
}