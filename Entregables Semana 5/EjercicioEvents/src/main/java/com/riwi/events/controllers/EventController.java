package com.riwi.events.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @PostMapping("/create")
  public String inser(@RequestBody Events event) {
    ResponseEntity.ok(this.eventServices.save(event));
    return "redirect:/";
  }

  // @GetMapping
  // public ResponseEntity<List<Events>> getAll() {
  // return ResponseEntity.ok(this.eventServices.getAll());
  // }

  // @GetMapping(path = "/{id}")
  // public ResponseEntity<Events> getById(@PathVariable String id) {
  // return ResponseEntity.ok(this.eventServices.findById(id));
  // }

  @PostMapping("/update/{id}")
  public String update(@PathVariable String id, @RequestBody Events event) {
    this.eventServices.update(id, event);
    return "redirect:/";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable String id) {
    this.eventServices.delete(id);
    return "redirect:/";
  }
}
