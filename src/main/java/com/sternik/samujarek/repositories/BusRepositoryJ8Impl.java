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
public class BusRepositoryJ8Impl implements BusRepository {
    private List<Bus> buses = new ArrayList<Bus>() {
        {
            add(Bus.produceBus(1L, "Brought from Croatia.", 108L, "260.04", "Ikarus", new Date(), new BigDecimal("298799.99"),
                    Status.GASOLINE));
            add(Bus.produceBus(2L, "Original Jelcz.", 78L, "043", "Jelcz", new Date(), new BigDecimal("157000.00"),
                    Status.DIESEL));
            add(Bus.produceBus(3L, "Golden age unique city bus.", 55L, "PR110M", "Jelcz", new Date(), new BigDecimal("217321.27"), Status.GAS));
            add(Bus.produceBus(4L, "Best polish brand!", 126L, "Urbino 12", "Solaris", new Date(), new BigDecimal("1000000.00"),
                    Status.DIESEL));
            add(Bus.produceBus(5L, "Something modern.", 98L, "Trollina 8", "Solaris", new Date(), new BigDecimal("1234567.92"), Status.GASOLINE));
            add(Bus.produceBus(6L, "Bought on discount many years ago, requires some repairs.", 108L, "354-5", "Mercedes-Benz", new Date(), new BigDecimal("354987.79"), Status.GASOLINE));
        }
    };

    @Override
    public List<Bus> findAll() {
        return this.buses;
    }

    @Override
    public Bus readById(Long id) throws NoSuchBusException {
        return this.buses.stream().filter(p -> Objects.equals(p.getIdBus(), id)).findFirst()
                .orElseThrow(NoSuchBusException::new);
    }

    @Override
    public Bus create(Bus bus) {
        if (!buses.isEmpty()) {
            bus.setIdBus(
                    this.buses.stream().mapToLong(p -> p.getIdBus()).max().getAsLong() + 1);
        } else {
            bus.setIdBus(1L);
        }
        this.buses.add(bus);
        return bus;
    }

    @Override
    public Bus update(Bus bus) throws NoSuchBusException {
        for (int i = 0; i < this.buses.size(); i++) {
            if (Objects.equals(this.buses.get(i).getIdBus(), bus.getIdBus())) {
                this.buses.set(i, bus);
                return bus;
            }
        }
        throw new NoSuchBusException("Nie ma takiej buses: " + bus.getIdBus());
    }

    @Override
    public void deleteById(Long id) throws NoSuchBusException {
        for (int i = 0; i < this.buses.size(); i++) {
            if (Objects.equals(this.buses.get(i).getIdBus(), id)) {
                this.buses.remove(i);
            }
        }
        throw new NoSuchBusException("Nie ma takiej buses: " + id);
    }
}