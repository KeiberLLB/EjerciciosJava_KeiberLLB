package com.riwi.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.events.entities.Events;


@Repository
public interface EventRepository extends JpaRepository<Events, String> {

} 