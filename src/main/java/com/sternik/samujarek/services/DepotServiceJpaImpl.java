package com.sternik.samujarek.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.repositories.springdata.BusRepository;

@Service
@Qualifier("spring-data")
public class DepotServiceJpaImpl implements DepotService {
	@Autowired
	private BusRepository db;
	
    @Override
    public List<Bus> findAll() {
        List<Bus> l = new ArrayList<>();
        for (bus item : db.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public List<Bus> findAllToSell() {
        List<Bus> l = new ArrayList<>();
        for (Bus item : db.findAll()) {
            l.add(item);
        }
        return l;
    }

    @Override
    public Optional<Bus> findById(Long id) {
        return Optional.ofNullable(db.findByIdBus(id));
    }

    @Override
    public Optional<Bus> create(Bus bus) {
        return Optional.of(db.save(bus));
    }

    @Override
    public Optional<Bus> edit(Bus bus) {
        return Optional.of(db.save(bus));
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        db.delete(id.intValue());
        return Optional.of(Boolean.TRUE);
    }

    @Override
    public List<Bus> findLatest3() {
        return Collections.emptyList();
    }
}