package edu.ifes.ci.si.les.ats.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.Cliente;
import edu.ifes.ci.si.les.ats.model.Dispositivo;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Integer> {

    @Transactional(readOnly = true)
	public Collection<Dispositivo> findByCliente(Cliente cliente);

}
