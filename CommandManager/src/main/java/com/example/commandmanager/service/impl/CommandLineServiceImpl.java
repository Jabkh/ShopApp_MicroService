package com.example.commandmanager.service.impl;

import com.example.commandmanager.client.ProductClient;
import com.example.commandmanager.dto.command.CommandDtoRequest;
import com.example.commandmanager.dto.command.CommandDtoResponse;
import com.example.commandmanager.dto.commandLine.CommandLineDtoRequest;
import com.example.commandmanager.dto.commandLine.CommandLineDtoResponse;
import com.example.commandmanager.model.Command;
import com.example.commandmanager.model.CommandLine;
import com.example.commandmanager.repository.CommandLineRepository;
import com.example.commandmanager.repository.CommandRepository;
import com.example.commandmanager.service.ICommandLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommandLineServiceImpl implements ICommandLineService {

    private final CommandLineRepository commandLineRepository;
    private final CommandRepository commandRepository;
    private final ProductClient productClient;


    @Autowired
    public CommandLineServiceImpl(CommandLineRepository commandLineRepository, CommandRepository commandRepository, ProductClient productClient) {
        this.commandLineRepository = commandLineRepository;
        this.commandRepository = commandRepository;
        this.productClient = productClient;
    }

    @Override
    public List<CommandLineDtoResponse> getAllCommandLinesByCommandId(int commandId) {
        Command command = commandRepository.findById(commandId).orElse(null);
        if (command != null) {
            List<CommandLine> commandLineList = commandLineRepository.findAllLinesByCommandId(commandId);
            return commandLineList.stream()
                    .map(this::convertToDto)
                    .toList();
        } else {
            throw new RuntimeException("Command not found");
        }
    }

    @Override
    public CommandLineDtoResponse createCommandLine(CommandLineDtoRequest commandLineDtoRequest, int customerId) {
        Command command = commandRepository.findById(commandLineDtoRequest.getCommandId()).orElse(null);

        CommandLine newCommandLine = CommandLine.builder()
                .quantity(commandLineDtoRequest.getQuantity())
                .subTotal(commandLineDtoRequest.getSubTotal())
                .productId(commandLineDtoRequest.getProductId())
                .build();

        if (command != null) {
            newCommandLine.setCommand(command);
            commandLineRepository.save(newCommandLine);

            command.setTotal(command.getTotal() + newCommandLine.getSubTotal());
            command.getCommandLines().add(newCommandLine);
            commandRepository.save(command);

            return convertToDto(newCommandLine);
        } else {
            CommandDtoResponse newCommand = createCommand(CommandDtoRequest.builder()
                    .date(LocalDate.now().toString())
                    .customerId(customerId)
                    .build());

            newCommandLine.setCommand(commandRepository.findById(newCommand.getId()).orElse(null));
            commandLineRepository.save(newCommandLine);

            return convertToDto(newCommandLine);
        }
    }

    @Override
    public CommandLineDtoResponse updateCommandLine(int id, CommandLineDtoRequest commandLineDtoRequest) {
        return null;
    }

    @Override
    public boolean deleteCommandLine(int id) {
        return false;
    }

    public CommandLineDtoResponse convertToDto(CommandLine commandLine) {
        return CommandLineDtoResponse.builder()
                .id(commandLine.getId())
                .quantity(commandLine.getQuantity())
                .subTotal(commandLine.getSubTotal())
                .product(productClient.getProductById(commandLine.getProductId()))
                .commandId(commandLine.getCommand().getId())
                .build();
    }

    private CommandDtoResponse createCommand(CommandDtoRequest commandDtoRequest) {
        Command newCommand = Command.builder()
                .status("EN COURS")
                .date(LocalDate.parse(commandDtoRequest.getDate()))
                .customerId(commandDtoRequest.getCustomerId())
                .build();
        commandRepository.save(newCommand);
        return CommandDtoResponse.builder()
                .id(newCommand.getId())
                .status(newCommand.getStatus())
                .date(newCommand.getDate())
                .customerId(newCommand.getCustomerId())
                .build();
    }
}