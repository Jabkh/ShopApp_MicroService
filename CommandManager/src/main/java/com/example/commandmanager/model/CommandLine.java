package com.example.commandmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommandLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double quantity;
    private double subTotal;
    private int productId;
    @ManyToOne
    @JoinColumn(name = "command_id")
    private Command command;

    @Override
    public String toString() {
        return "CommandLine{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", subTotal=" + subTotal +
                ", productId=" + productId +
                '}';
    }
}
