package pl.sternik.kk.week.repositories;

import java.util.List;

import pl.sternik.kk.week.entities.Moneta;


public interface MonetyRepository {
    Moneta create(Moneta moneta) throws MonetaAlreadyExistsException;
    Moneta readById(Long id) throws NoSuchMonetaException;
    Moneta update(Moneta moneta) throws NoSuchMonetaException;
    void deleteById(Long id) throws NoSuchMonetaException;
    List<Moneta> findAll();
}