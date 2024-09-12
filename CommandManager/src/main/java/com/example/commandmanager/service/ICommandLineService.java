package com.example.commandmanager.service;

import com.example.commandmanager.dto.commandLine.CommandLineDtoRequest;
import com.example.commandmanager.dto.commandLine.CommandLineDtoResponse;
import com.example.commandmanager.model.CommandLine;

import java.util.List;

public interface ICommandLineService {

    List<CommandLineDtoResponse> getAllCommandLinesByCommandId(int commandId);

    CommandLineDtoResponse createCommandLine(CommandLineDtoRequest commandLineDtoRequest, int customerId);

    CommandLineDtoResponse updateCommandLine(int id, CommandLineDtoRequest commandLineDtoRequest);

    boolean deleteCommandLine(int id);
}
