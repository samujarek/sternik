package com.sternik.samujarek.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.repositories.MonetaAlreadyExistsException;
import com.sternik.samujarek.repositories.MonetyRepository;
import com.sternik.samujarek.repositories.NoSuchMonetaException;


@Service
@Qualifier("tablica")
public class KlaserServiceImpl implements KlaserService {

    @Autowired
    @Qualifier("tablica")
    private MonetyRepository bazaDanych;

    @Override
    public List<Bus> findAll() {
        return bazaDanych.findAll();
    }

    @Override
    public List<Bus> findAllToSell() {
        return bazaDanych.findAll();
    }

    @Override
    public Optional<Bus> findById(Long id) {
        try {
            return Optional.of(bazaDanych.readById(id));
        } catch (NoSuchMonetaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bus> create(Bus bus) {
        try {
            return Optional.of(bazaDanych.create(bus));
        } catch (MonetaAlreadyExistsException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bus> edit(Bus bus) {
        try {
            return Optional.of(bazaDanych.update(bus));
        } catch (NoSuchMonetaException e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            bazaDanych.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMonetaException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Bus> findLatest3() {
        return Collections.emptyList();
    }

}
