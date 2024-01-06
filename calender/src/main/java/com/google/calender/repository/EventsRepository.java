package com.google.calender.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.calender.model.Events;

public interface EventsRepository extends JpaRepository<Events,String>{
}
