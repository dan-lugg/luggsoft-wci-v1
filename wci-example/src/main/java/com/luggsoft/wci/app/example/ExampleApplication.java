package com.luggsoft.wci.app.example;

import com.luggsoft.wci.core.notifications.NotificationLevel;
import com.luggsoft.wci.core.notifications.NotificationStore;
import com.luggsoft.wci.core.process.DefaultProcessContext;
import com.luggsoft.wci.core.process.ProcessManager;
import com.luggsoft.wci.module.spring.EnableWci;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.UUID;

@EnableWci
@SpringBootApplication
public class ExampleApplication
{
    @SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
    public static void main(String[] args) throws InterruptedException
    {
        var applicationContext = SpringApplication.run(ExampleApplication.class, args);

        var processManager = applicationContext.getBean(ProcessManager.class);

        var notificationStore = applicationContext.getBean(NotificationStore.class);

        var processId = UUID.fromString("00000000-0000-0000-0000-000000000000");

        var processContext = new DefaultProcessContext(processId, notificationStore);

        processManager.startProcess(processContext, (myProcessContext) -> {
            var random = new Random();

            try
            {
                while (true)
                {
                    var payload = "Hello world!";
                    var level = getRandomNotificationLevel(random);


                    myProcessContext.sendNotification(level, payload);
                    Thread.sleep(3_000);

                    System.out.printf("For %s, there are %d notifications%n", processId, notificationStore.size());
                }
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        });
    }

    private static NotificationLevel getRandomNotificationLevel(Random random)
    {
        var notificationLevels = NotificationLevel.values();
        return notificationLevels[random.nextInt(notificationLevels.length)];
    }
}
