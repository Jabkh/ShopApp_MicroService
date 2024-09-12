package com.example.commandmanager.repository;

import com.example.commandmanager.model.Command;
import com.example.commandmanager.model.CommandLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommandLineRepository extends JpaRepository<CommandLine, Integer> {
    List<CommandLine> findAllLinesByCommandId(int command_id);
}

