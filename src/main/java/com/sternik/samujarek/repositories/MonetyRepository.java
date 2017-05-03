package com.sternik.samujarek.repositories;

import java.util.List;

import com.sternik.samujarek.entities.Bus;


public interface MonetyRepository {
    Bus create(Bus bus) throws MonetaAlreadyExistsException;
    Bus readById(Long id) throws NoSuchMonetaException;
    Bus update(Bus bus) throws NoSuchMonetaException;
    void deleteById(Long id) throws NoSuchMonetaException;
    List<Bus> findAll();
}