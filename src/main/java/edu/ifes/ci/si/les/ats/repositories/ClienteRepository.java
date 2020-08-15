package edu.ifes.ci.si.les.ats.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT CLIENTE.*  FROM ordem_de_servico INNER JOIN dispositivo ON ordem_de_servico.dispositivo_id = dispositivo.id INNER JOIN cliente on dispositivo.cliente_id = cliente. id where ordem_de_servico.pagamento = 'Nao'", nativeQuery = true)
	public Collection<Cliente> findDevedores();

}
