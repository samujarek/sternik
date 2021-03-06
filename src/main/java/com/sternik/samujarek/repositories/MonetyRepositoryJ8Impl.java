package com.sternik.samujarek.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.entities.Status;


@Service
@Qualifier("lista")
public class MonetyRepositoryJ8Impl implements MonetyRepository {

    private List<Bus> monety = new ArrayList<Bus>() {
        {
            add(Bus.produceMoneta(1L, "Polska", 1L, "zł", "Ładna nowiutka złotóweczka", new Date(), new BigDecimal("1.2"),
                    Status.NOWA));
            add(Bus.produceMoneta(2L, "Polska", 1L, "zł", "First Moneta", new Date(), new BigDecimal("1.2"),
                    Status.DO_SPRZEDANIA));
            add(Bus.produceMoneta(3L, "Polska", 1L, "zł", "Second Moneta", new Date(), new BigDecimal("1.2"), Status.DUBLET));
            add(Bus.produceMoneta(4L, "Polska", 1L, "zł", "Forth Moneta", new Date(), new BigDecimal("1.2"),
                    Status.DO_SPRZEDANIA));
            add(Bus.produceMoneta(5L, "Polska", 1L, "zł", "Moneta Number 5", new Date(), new BigDecimal("1.2"), Status.NOWA));
            add(Bus.produceMoneta(6L, "Polska", 1L, "zł", "Sixth Moneta", new Date(), new BigDecimal("1.2"), Status.NOWA));
        }
    };

    @Override
    public List<Bus> findAll() {
        return this.monety;
    }

    @Override
    public Bus readById(Long id) throws NoSuchMonetaException {
        return this.monety.stream().filter(p -> Objects.equals(p.getNumerKatalogowy(), id)).findFirst()
                .orElseThrow(NoSuchMonetaException::new);
    }

    @Override
    public Bus create(Bus bus) {
        if (!monety.isEmpty()) {
            bus.setNumerKatalogowy(
                    this.monety.stream().mapToLong(p -> p.getNumerKatalogowy()).max().getAsLong() + 1);
        } else {
            bus.setNumerKatalogowy(1L);
        }
        this.monety.add(bus);
        return bus;
    }

    @Override
    public Bus update(Bus bus) throws NoSuchMonetaException {
        for (int i = 0; i < this.monety.size(); i++) {
            if (Objects.equals(this.monety.get(i).getNumerKatalogowy(), bus.getNumerKatalogowy())) {
                this.monety.set(i, bus);
                return bus;
            }
        }
        throw new NoSuchMonetaException("Nie ma takiej Monety: " + bus.getNumerKatalogowy());
    }

    @Override
    public void deleteById(Long id) throws NoSuchMonetaException {
        for (int i = 0; i < this.monety.size(); i++) {
            if (Objects.equals(this.monety.get(i).getNumerKatalogowy(), id)) {
                this.monety.remove(i);
            }
        }
        throw new NoSuchMonetaException("Nie ma takiej Monety: " + id);
    }

}
