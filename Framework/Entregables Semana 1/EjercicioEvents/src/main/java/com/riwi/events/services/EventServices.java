package com.riwi.events.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.riwi.events.entities.Events;
import com.riwi.events.repository.EventRepository;
import com.riwi.events.services.services_abstract.IEventServices;

@Service
public class EventServices implements IEventServices {
  @Autowired
  private EventRepository eventRepository;

  @Override
  public Events save(Events event) {
    return this.eventRepository.save(event);
  }

  public Page<Events> getAll(int page, int size) {
    page = (page < 0) ? 0 : page;
    PageRequest pagination = PageRequest.of(page, size);
    return this.eventRepository.findAll(pagination);
  }

  @Override
  public Events findById(String id) {
    return this.eventRepository.findById(id).orElseThrow();
  }

  @Override
  public void delete(String id) {
    this.eventRepository.findById(id).orElseThrow();
    this.eventRepository.deleteById(id);
  }

  @Override
  public Events update(String id, Events event) {
    this.eventRepository.findById(id).orElseThrow();
    event.setId(id);
    return this.eventRepository.save(event);
  }

}
