package com.luggsoft.wci.app.example.commands;

import com.luggsoft.wci.core.commands.async.AsyncCommandResult;
import lombok.Data;

import java.util.UUID;

@Data
public class ClearDataAsyncCommandResult implements AsyncCommandResult {
    private UUID processId;
}

