package com.sternik.samujarek.entities.repositories;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sternik.samujarek.entities.entities.Bus;
import com.sternik.samujarek.entities.entities.Status;


@Repository
@Qualifier("tablica")
public class ProstaBazaDanych implements MonetyRepository {

    private Bus[] baza;

    public ProstaBazaDanych() {
        baza = new Bus[15];
        Bus m = new Bus();
        m.setNumerKatalogowy(0L);
        m.setKrajPochodzenia("Polska");
        m.setNominal(1L);
        m.setWaluta("zł");
        m.setOpis("Ładna nowiutka złotóweczka");
        m.setDataNabycia(new Date());
        m.setCenaNabycia(new BigDecimal("1.2"));
        m.setStatus(Status.NOWA);
        baza[0] = m;
        m = new Bus();
        m.setNumerKatalogowy(2L);
        m.setKrajPochodzenia("Polska");
        m.setNominal(2L);
        m.setWaluta("zł");
        m.setOpis("Ładna nowiutka dwu złotóweczka");
        m.setDataNabycia(new Date());
        m.setCenaNabycia(new BigDecimal("2.2"));
        m.setStatus(Status.DO_SPRZEDANIA);
        baza[2] = m;

    }

    public ProstaBazaDanych(int rozmiarBazy) {
        baza = new Bus[rozmiarBazy];
    }

    @Override
    public Bus create(Bus bus) throws MonetaAlreadyExistsException {
        if (bus.getNumerKatalogowy() != null && baza[bus.getNumerKatalogowy().intValue()] != null) {
            if (bus.getNumerKatalogowy().equals(baza[bus.getNumerKatalogowy().intValue()].getNumerKatalogowy())) {
                throw new MonetaAlreadyExistsException("Już jest moneta o takim numerze.");
            }
        }
        for (int i = 0; i < baza.length; i++) {
            if (baza[i] == null) {
                baza[i] = bus;
                bus.setNumerKatalogowy((long) i);
                return bus;
            }
        }
        throw new RuntimeException("Brak miejsca w tablicy");
    }

    @Override
    public void deleteById(Long id) throws NoSuchMonetaException {
        int numerKatalogowy = id.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchMonetaException("Nie poprawny numer katologowy");
        }
        // tu troche zle ;)
        baza[numerKatalogowy] = null;
    }

    @Override
    public Bus update(Bus bus) throws NoSuchMonetaException {
        int numerKatalogowy = bus.getNumerKatalogowy().intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(numerKatalogowy)) {
            throw new NoSuchMonetaException("Nie poprawny numer katologowy");
        }

        Bus m = baza[bus.getNumerKatalogowy().intValue()];
        if (m == null) {
            throw new NoSuchMonetaException("Brak takiej monety.");
        } else {
            baza[bus.getNumerKatalogowy().intValue()] = bus;
        }
        return bus;
    }

    @Override
    public Bus readById(Long numerKatalogowy) throws NoSuchMonetaException {
        int id = numerKatalogowy.intValue();
        if (!sprawdzPoprawnoscNumeruKatalogowego(id) || czyWolne(id)) {
            throw new NoSuchMonetaException();
        }
        return baza[id];
    }

    private boolean czyWolne(int id) {
        if(baza[id]!= null)
            return false;
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