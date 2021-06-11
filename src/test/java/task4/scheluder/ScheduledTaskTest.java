package task4.scheluder;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Unit test for {@link ScheduledTask}
 *
 * @author Dmitrii_Mishenev
 */
class ScheduledTaskTest {

    @Test
    void taskProvided_run_shouldInvokeTerminationCallback() {
        Runnable task = Mockito.mock(Runnable.class);
        Runnable terminationCallback = Mockito.mock(Runnable.class);
        ScheduledTask scheduledTask = new ScheduledTask(task, terminationCallback);

        scheduledTask.run();

        Mockito.verify(task).run();
        Mockito.verify(terminationCallback).run();
    }
}