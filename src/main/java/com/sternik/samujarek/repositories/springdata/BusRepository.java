package com.sternik.samujarek.repositories.springdata;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sternik.samujarek.entities.Bus;

public interface BusRepository extends JpaRepository<Bus, Integer> {
	public Bus findByIdBus(long id);
}