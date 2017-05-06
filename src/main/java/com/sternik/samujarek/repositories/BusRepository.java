package com.sternik.samujarek.repositories;

import java.util.List;

import com.sternik.samujarek.entities.Bus;

public interface BusRepository {
    Bus create(Bus bus) throws BusAlreadyExistsException;
    Bus readById(Long id) throws NoSuchBusException;
    Bus update(Bus bus) throws NoSuchBusException;
    void deleteById(Long id) throws NoSuchBusException;
    List<Bus> findAll();
}