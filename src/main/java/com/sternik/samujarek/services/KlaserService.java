package com.sternik.samujarek.services;

import java.util.List;
import java.util.Optional;

import com.sternik.samujarek.entities.Bus;


public interface KlaserService {
    List<Bus> findAll();

    List<Bus> findAllToSell();

    Optional<Bus> findById(Long id);

    Optional<Bus> create(Bus bus);

    Optional<Bus> edit(Bus bus);

    Optional<Boolean> deleteById(Long id);

    List<Bus> findLatest3();
}