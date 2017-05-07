package com.sternik.samujarek.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sternik.samujarek.entities.Bus;
import com.sternik.samujarek.entities.Status;

@Repository
@Qualifier("tablica")
public class SimpleDatabase implements BusRepository {
	private Bus[] baza;

	public SimpleDatabase() {
		baza = new Bus[15];
		Bus m = new Bus();
		m.setIdBus(0L);
		m.setVehicleHistory("Brought from Croatia.");
		m.setPassengersCapacity(108L);
		m.setBrandVersionBus("260.04");
		m.setBrandBus("Ikarus");
		m.setPurchaseDate(new Date());
		m.setVehiclePrice(new BigDecimal("298799.99"));
		m.setStatus(Status.GASOLINE);
		baza[0] = m;
		m = new Bus();
		m.setIdBus(2L);
		m.setVehicleHistory("Original Jelcz.");
		m.setPassengersCapacity(75L);
		m.setBrandVersionBus("043");
		m.setBrandBus("Jelcz");
		m.setPurchaseDate(new Date());
		m.setVehiclePrice(new BigDecimal("157000.00"));
		m.setStatus(Status.DIESEL);
		baza[2] = m;
	}

	public SimpleDatabase(int rozmiarBazy) {
		baza = new Bus[rozmiarBazy];
	}

	@Override
	public Bus create(Bus bus) throws BusAlreadyExistsException {
		if (bus.getIdBus() != null && baza[bus.getIdBus().intValue()] != null) {
			if (bus.getIdBus().equals(baza[bus.getIdBus().intValue()].getIdBus())) {
				throw new BusAlreadyExistsException("Już jest moneta o takim numerze.");
			}
		}

		for (int i = 0; i < baza.length; i++) {
			if (baza[i] == null) {
				baza[i] = bus;
				bus.setIdBus((long) i);
				return bus;
			}
		}
		throw new RuntimeException("Brak miejsca w tablicy");
	}

	@Override
	public void deleteById(Long id) throws NoSuchBusException {
		int numerKatalogowy = id.intValue();
		if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
			throw new NoSuchBusException("Nie poprawny numer katologowy");
		}
		baza[numerKatalogowy] = null;
	}

	@Override
	public Bus update(Bus bus) throws NoSuchBusException {
		int numerKatalogowy = bus.getIdBus().intValue();
		if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
			throw new NoSuchBusException("Nie poprawny numer katologowy");
		}

		Bus m = baza[bus.getIdBus().intValue()];
		if (m == null) {
			throw new NoSuchBusException("Brak takiej monety.");
		} else {
			baza[bus.getIdBus().intValue()] = bus;
		}
		return bus;
	}

	@Override
	public Bus readById(Long numerKatalogowy) throws NoSuchBusException {
		int id = numerKatalogowy.intValue();

		if (!sprawdzPoprawnoscNumeruKatalogowego(id) || czyWolne(id)) {
			throw new NoSuchBusException();
		}
		return baza[id];
	}

	private boolean czyWolne(int id) {
		if (baza[id] != null) {
			return false;
		}
		return true;
	}

	@Override
	public List<Bus> findAll() {
		List<Bus> tmp = new ArrayList<>();
		for (int i = 0; i < baza.length; i++) {
			if (baza[i] != null)
				tmp.add(baza[i]);
		}
		return tmp;
	}

	public void wyswietlBaze() {
		for (int i = 0; i < baza.length; i++) {
			System.out.println("" + i + ":" + baza[i]);
		}
	}

	private boolean sprawdzPoprawnoscNumeruKatalogowego(int numerKatalogowy) {
		if (numerKatalogowy < 0 || numerKatalogowy >= baza.length) {
			System.out.println("Zły numer katalogowy");
			return false;
		}
		return true;
	}
}