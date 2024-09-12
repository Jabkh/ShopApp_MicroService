package com.example.commandmanager.service;

import com.example.commandmanager.dto.command.CommandDtoRequest;
import com.example.commandmanager.dto.command.CommandDtoResponse;
import com.example.commandmanager.dto.commandAndLines.CommandAndLinesDtoResponse;
import com.example.commandmanager.model.Command;

import java.util.List;

public interface ICommandService {
    List<CommandDtoResponse> getAllCommands();

    CommandAndLinesDtoResponse getCommandByIdWithLines(int id);

    CommandDtoResponse getCommandById(int id);

    CommandDtoResponse createCommand(CommandDtoRequest command);
}
