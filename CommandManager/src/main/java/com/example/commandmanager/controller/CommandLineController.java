package com.example.commandmanager.controller;

import com.example.commandmanager.dto.commandLine.CommandLineDtoRequest;
import com.example.commandmanager.dto.commandLine.CommandLineDtoResponse;
import com.example.commandmanager.service.impl.CommandLineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/command-lines")
public class CommandLineController {

    @Autowired
    private CommandLineServiceImpl commandLineService;

    @PostMapping("/create")
    public ResponseEntity<CommandLineDtoResponse> createCommandLine(@RequestBody CommandLineDtoRequest commandLineDtoRequest, @Param("customerId") int customerId) {
        return ResponseEntity.ok(commandLineService.createCommandLine(commandLineDtoRequest, customerId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandLineDtoResponse> updateCommandLine(@PathVariable("id") int id, @RequestBody CommandLineDtoRequest commandLineDtoRequest) {
        return ResponseEntity.ok(commandLineService.updateCommandLine(id, commandLineDtoRequest));
    }



}

