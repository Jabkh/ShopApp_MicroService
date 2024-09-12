package com.example.commandmanager.controller;

import com.example.commandmanager.dto.command.CommandDtoRequest;
import com.example.commandmanager.dto.command.CommandDtoResponse;
import com.example.commandmanager.dto.commandAndLines.CommandAndLinesDtoResponse;
import com.example.commandmanager.service.impl.CommandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commands")
public class CommandController {

    @Autowired
    private CommandServiceImpl commandService;
    
    @GetMapping
    public ResponseEntity<List<CommandDtoResponse>> getAllCommands() {
        return ResponseEntity.ok(commandService.getAllCommands());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandDtoResponse> getCommandById(@PathVariable("id") int id) {
        return ResponseEntity.ok(commandService.getCommandById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<CommandDtoResponse> createCommand(@RequestBody CommandDtoRequest command) {
        return ResponseEntity.ok(commandService.createCommand(command));
    }

    // i want to get command by id include his lines
    @GetMapping("/{id}/lines")
    public ResponseEntity<CommandAndLinesDtoResponse> getCommandByIdWithLines(@PathVariable("id") int id) {
        return ResponseEntity.ok(commandService.getCommandByIdWithLines(id));
    }



}
