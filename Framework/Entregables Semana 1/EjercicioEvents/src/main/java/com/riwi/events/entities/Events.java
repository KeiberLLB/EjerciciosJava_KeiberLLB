package com.riwi.events.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String nombre;
  private LocalDate fecha;
  private String ubicacion;
  private int capacidad;
}
