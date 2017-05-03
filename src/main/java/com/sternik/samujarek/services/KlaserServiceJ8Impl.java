package com.sternik.samujarek.entities.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sternik.samujarek.entities.entities.Bus;
import com.sternik.samujarek.entities.entities.Status;
import com.sternik.samujarek.entities.repositories.MonetaAlreadyExistsException;
import com.sternik.samujarek.entities.repositories.MonetyRepository;
import com.sternik.samujarek.entities.repositories.NoSuchMonetaException;


@Service
@Primary
public class KlaserServiceJ8Impl implements KlaserService {

    @Autowired
    @Qualifier("lista")
    private MonetyRepository monety;

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
        } catch (NoSuchMonetaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Bus> create(Bus bus) {
        try {
            return Optional.of(monety.create(bus));
        } catch (MonetaAlreadyExistsException e) {
            try {
                return Optional.of(monety.readById(bus.getNumerKatalogowy()));
            } catch (NoSuchMonetaException e1) {
                return Optional.empty();
            }
        }

    }

    @Override
    public Optional<Bus> edit(Bus bus) {
        try {
            return Optional.of(monety.update(bus));
        } catch (NoSuchMonetaException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Boolean> deleteById(Long id) {
        try {
            monety.deleteById(id);
            return Optional.of(Boolean.TRUE);
        } catch (NoSuchMonetaException e) {
            return Optional.of(Boolean.FALSE);
        }
    }

    @Override
    public List<Bus> findAllToSell() {
        return monety.findAll().stream().filter(p -> Objects.equals(p.getStatus(), Status.DO_SPRZEDANIA))
                .collect(Collectors.toList());
    }
}
