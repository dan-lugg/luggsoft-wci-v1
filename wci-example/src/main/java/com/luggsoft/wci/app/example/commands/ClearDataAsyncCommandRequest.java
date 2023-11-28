package com.luggsoft.wci.app.example.commands;

import com.luggsoft.wci.core.commands.async.AsyncCommandRequest;
import lombok.Data;

import java.time.Instant;

@Data
public class ClearDataAsyncCommandRequest implements AsyncCommandRequest<ClearDataAsyncCommandResult> {
    private Instant since;
}
