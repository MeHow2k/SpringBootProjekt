package controllers.repositories;

import controllers.models.Wypozyczenie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WypozyczenieRepository extends JpaRepository<Wypozyczenie, Integer> {


    public Wypozyczenie findById(int id);
    public List<Wypozyczenie> findByCzytelnikId(int id);
    public List<Wypozyczenie> findByKsiazkaId(int id);
    public List<Wypozyczenie> findByReturndate(String text);
}