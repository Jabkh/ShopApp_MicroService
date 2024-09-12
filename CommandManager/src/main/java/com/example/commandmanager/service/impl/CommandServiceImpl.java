package com.example.commandmanager.service.impl;

import com.example.commandmanager.dto.command.CommandDtoRequest;
import com.example.commandmanager.dto.command.CommandDtoResponse;
import com.example.commandmanager.dto.commandAndLines.CommandAndLinesDtoResponse;
import com.example.commandmanager.dto.commandLine.CommandLineDtoResponse;
import com.example.commandmanager.model.Command;
import com.example.commandmanager.repository.CommandRepository;
import com.example.commandmanager.service.ICommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommandServiceImpl implements ICommandService {

    private final CommandRepository commandRepository;
    private final CommandLineServiceImpl commandLineServiceImpl;

    @Autowired
    public CommandServiceImpl(CommandRepository commandRepository, CommandLineServiceImpl commandLineServiceImpl) {
        this.commandRepository = commandRepository;
        this.commandLineServiceImpl = commandLineServiceImpl;
    }

    @Override
    public List<CommandDtoResponse> getAllCommands() {
        List<Command> commandList = commandRepository.findAll();
        return commandList.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public CommandAndLinesDtoResponse getCommandByIdWithLines(int id) {
        Command command = commandRepository.findById(id).orElse(null);
        if (command != null) {
            List<CommandLineDtoResponse> commandLineList = commandLineServiceImpl.getAllCommandLinesByCommandId(id);
            return CommandAndLinesDtoResponse.builder()
                    .id(command.getId())
                    .status(command.getStatus())
                    .date(command.getDate())
                    .customerId(command.getCustomerId())
                    .commandLines(commandLineList)
                    .build();
        } else {
            throw new RuntimeException("Command not found");
        }
    }


    @Override
    public CommandDtoResponse getCommandById(int id) {
        Command command = commandRepository.findById(id).orElse(null);
        if (command != null) {
            return convertToDto(command);
        } else {
            throw new RuntimeException("Command not found");
        }
    }

    @Override
    public CommandDtoResponse createCommand(CommandDtoRequest command) {
        Command newCommand = Command.builder()
                .status("EN COURS")
                .date(LocalDate.parse(command.getDate()))
                .customerId(command.getCustomerId())
                .build();
        commandRepository.save(newCommand);
        return convertToDto(newCommand);

    }

    public CommandDtoResponse convertToDto(Command command) {
        return CommandDtoResponse.builder()
                .id(command.getId())
                .status(command.getStatus())
                .date(command.getDate())
                .customerId(command.getCustomerId())
                .build();
    }



}
