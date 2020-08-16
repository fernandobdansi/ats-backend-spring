package edu.ifes.ci.si.les.ats.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.OrdemDeServico;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Integer> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT ordem_de_servico.*  FROM feed_back INNER JOIN ordem_de_servico ON feed_back.ordemdeservico_id = ordem_de_servico.id", nativeQuery = true)
	public Collection<OrdemDeServico> findOrdemFeedBack();

}
