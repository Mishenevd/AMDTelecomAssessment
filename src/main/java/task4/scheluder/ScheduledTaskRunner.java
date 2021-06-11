package task4.scheluder;

import static task4.logging.LoggerConfiguration.LOGGER;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

/**
 * Schedule tasks with selected interval.
 *
 * @author Dmitrii_Mishenev
 */
public class ScheduledTaskRunner {
    private static final String TERMINATE_MSG = "Terminating task";

    private final int executionTimes;
    private final int executionPeriod;
    private final TimeUnit measure;

    public ScheduledTaskRunner(int executionTimes, int executionPeriod, TimeUnit measure) {
        this.executionTimes = executionTimes;
        this.executionPeriod = executionPeriod;
        this.measure = measure;
    }

    /**
     * Execute task.
     * @param task task to execute
     */
    public void executeTask(Runnable task) {
        final AtomicInteger taskInvocationCounter = new AtomicInteger(executionTimes);
        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        final Runnable terminationCallback = () -> {
            if (taskInvocationCounter.decrementAndGet() == 0) {
                LOGGER.log(Level.FINE, TERMINATE_MSG);
                executorService.shutdown();
            }
        };

        executorService.scheduleAtFixedRate(new ScheduledTask(task, terminationCallback), 0, executionPeriod, measure);
    }
}