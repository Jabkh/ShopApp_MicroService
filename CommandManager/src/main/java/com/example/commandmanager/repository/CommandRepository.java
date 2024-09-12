package com.example.commandmanager.repository;

import com.example.commandmanager.dto.command.CommandDtoResponse;
import com.example.commandmanager.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommandRepository extends JpaRepository<Command, Integer> {

    // i want to get command by id include his lines
    @Query("SELECT c FROM Command c LEFT JOIN FETCH c.commandLines WHERE c.id = :id")
    Command getCommandByIdWithLines(@Param("id") int id);
}
