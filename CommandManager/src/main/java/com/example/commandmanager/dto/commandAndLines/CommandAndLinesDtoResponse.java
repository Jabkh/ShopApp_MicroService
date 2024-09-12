package com.example.commandmanager.dto.commandAndLines;

import com.example.commandmanager.dto.commandLine.CommandLineDtoResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CommandAndLinesDtoResponse {
    private int id;
    private String status;
    private LocalDate date;
    private int customerId;
    private List<CommandLineDtoResponse> commandLines;
}
