package com.luggsoft.wci.app.example.commands;

import com.luggsoft.wci.core.commands.async.AsyncCommandHandlerBase;
import com.luggsoft.wci.core.notifications.NotificationLevel;
import com.luggsoft.wci.core.process.ProcessDescriptor;
import com.luggsoft.wci.core.process.ProcessFunction;
import com.luggsoft.wci.core.process.ProcessManager;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ClearDataHandlerAsyncCommandHandler extends AsyncCommandHandlerBase<ClearDataAsyncCommandRequest, ClearDataAsyncCommandResult>
{

    private final ProcessManager processManager;

    @NotNull
    @Override
    protected ProcessManager getProcessManager()
    {
        return this.processManager;
    }

    public ClearDataHandlerAsyncCommandHandler(ProcessManager processManager)
    {
        this.processManager = processManager;
    }

    @NotNull
    @Override
    protected ProcessFunction createProcessFunction()
    {
        return processContext -> {
            var processId = UUID.randomUUID();

            try
            {
                for (int index = 0; index < 10; index++)
                {
                    processContext.sendNotification(NotificationLevel.GENERAL, "Clearing data...");
                    Thread.sleep(1_000);

                    processContext.sendNotification(NotificationLevel.SUCCESS, "Done clearing data!");

                }

            }
            catch (InterruptedException exception)
            {
                processContext.sendNotification(NotificationLevel.FAILURE, "Failed to clear data!");
                throw new RuntimeException(exception);
            }

            return NotificationLevel.SUCCESS;
        };
    }

    @NotNull
    @Override
    protected ClearDataAsyncCommandResult createCommandResult(@NotNull ProcessDescriptor processDescriptor)
    {
        return null;
    }

    /*
    @NotNull
    @Override
    protected AsyncCommandProcessRunnable<ClearDataRequest, CommandContext> getCommandRunnable(@NotNull ClearDataRequest request, @NotNull CommandContext context) {
        return new AsyncCommandProcessRunnable<>() {
            @Override
            public void run() {
                try {
                    for (var index = 0; index < 10; index++) {
                        Thread.sleep(1_000);
                        context.getActivityEmitter().emitActivity(new Activity<>(
                                Instant.now(),
                                String.format("Progress message #%d...", index)));
                    }

                    context.getActivityEmitter().emitActivity(new Activity<>(
                            Instant.now(),
                            String.format("Completion message for %s", this)));
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        };
    }
    */
}
