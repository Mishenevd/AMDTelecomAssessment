package task4.scheluder;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for {@link ScheduledTaskRunner}
 *
 * @author Dmitrii_Mishenev
 */
class ScheduledTaskRunnerTest {

    @Test
    void scheduleTenTimesSpecified_executeTask_shouldRunTenTimes() throws InterruptedException {
        int scheduleTimes = 10;
        ScheduledTaskRunner scheduledTaskRunner = new ScheduledTaskRunner(scheduleTimes, 100, TimeUnit.MILLISECONDS);
        Runnable mockedTask = Mockito.mock(Runnable.class);

        scheduledTaskRunner.executeTask(mockedTask);
        Thread.sleep(1000);

        Mockito.verify(mockedTask, times(scheduleTimes)).run();
    }
}