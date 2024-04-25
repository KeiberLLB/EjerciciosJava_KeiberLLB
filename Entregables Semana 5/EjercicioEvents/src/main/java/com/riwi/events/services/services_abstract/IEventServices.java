package com.riwi.events.services.services_abstract;

import java.util.List;

import com.riwi.events.entities.Events;

public interface IEventServices {

  public Events save(Events event);

  public List<Events> getAll();

  public Events findById(String id);

  public void delete(String id);

  public Events update(String id, Events event);

  // public List<Events> search(String name);

}