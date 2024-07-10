package com.example.taskmasters.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = ("serviceRequest"))
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String serviceType;
    private String description;
    private String address;
    private LocalDateTime requestTime;
    private LocalDateTime completionTime;
    @ManyToOne
    private Users customer;
    @ManyToOne
    private Users serviceProvider;
}
