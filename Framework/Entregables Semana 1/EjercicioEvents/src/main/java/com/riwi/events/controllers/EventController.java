package com.riwi.events.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.riwi.events.entities.Events;
import com.riwi.events.services.services_abstract.IEventServices;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/event")
@AllArgsConstructor
public class EventController {
  @Autowired
  private final IEventServices eventServices;

  @GetMapping
  public Page<Events> getAll(
      @RequestParam(defaultValue = "1") int page,
      @RequestParam(defaultValue = "10") int size) {
    return this.eventServices.getAll(page - 1, size);
  }

  @GetMapping(path = "/{id}")
  public ResponseEntity<Events> getById(@PathVariable String id) {
    return ResponseEntity.ok(this.eventServices.findById(id));
  }

  @PostMapping
  public ResponseEntity<Events> insert(@RequestBody Events objEvents) {
    LocalDate fechaActual = LocalDate.now();
    if (objEvents.getCapacidad() < 0 ||
        objEvents.getFecha().isBefore(fechaActual))
      return ResponseEntity.badRequest().build();
    return ResponseEntity.ok(this.eventServices.save(objEvents));
  }

  @PutMapping(path = "/{id}")
  public ResponseEntity<Events> update(
      @PathVariable String id,
      @RequestBody Events objEvents) {
    return ResponseEntity.ok(this.eventServices.update(id, objEvents));
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    this.eventServices.delete(id);
    return ResponseEntity.noContent().build();
  }

}
