package com.example.commandmanager.dto.commandAndLines;
import com.example.commandmanager.dto.commandLine.CommandLineDtoResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommandAndLinesDtoRequest {
    private String status;
    private String date;
    private int customerId;
    private List<CommandLineDtoResponse> commandLines;
}