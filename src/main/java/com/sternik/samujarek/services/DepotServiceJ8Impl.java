package com.sternik.samujarek.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.entities.Status;
import com.sternik.samujarek.repositories.BusAlreadyExistsException;
import com.sternik.samujarek.repositories.BusRepository;
import com.sternik.samujarek.repositories.NoSuchBusException;

@Service
@Primary
public class DepotServiceJ8Impl implements DepotService {

    @Autowired
    @Qualifier("lista")
    private BusRepository monety;

    @Override
    public List<Bus> findAll() {
        return monety.findAll();
    }

    @Override
    public List<Bus> findLatest3() {
        return monety.findAll().stream().sorted((a, b) -> b.getDataNabycia().compareTo(a.getDataNabycia())).limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Bus> findById(Long id) {
        try {
            return Optional.of(monety.readById(id));
        } catch (NoSuchBusException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bus> create(Bus bus) {
        try {
            return Optional.of(monety.create(bus));
        } catch (BusAlreadyExistsException e) {
            try {
                return Optional.of(monety.readById(bus.getNumerKatalogowy()));
            } catch (NoSuchBusException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Bus> edit(Bus bus) {
        try {
            return Optional.of(monety.update(bus));
        } catch (NoSuchBusException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            monety.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchBusException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Bus> findAllToSell() {
        return monety.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DIESEL))
                .collect(Collectors.toList());
    }
}
