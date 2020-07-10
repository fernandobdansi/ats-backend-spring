package edu.ifes.ci.si.les.ats.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.Marca;
import edu.ifes.ci.si.les.ats.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {

    @Transactional(readOnly = true)
    public Collection<Modelo> findByMarca(Marca marca);
	
}
