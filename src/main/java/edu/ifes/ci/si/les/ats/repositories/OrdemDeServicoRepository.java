package edu.ifes.ci.si.les.ats.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.OrdemDeServico;
import edu.ifes.ci.si.les.ats.model.StatusOrdemDeServico;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Integer> {

	@Transactional(readOnly = true)
	@Query(value = "SELECT ordem_de_servico.*  FROM feed_back INNER JOIN ordem_de_servico ON feed_back.ordemdeservico_id = ordem_de_servico.id", nativeQuery = true)
	public Collection<OrdemDeServico> findOrdemFeedBack();

	@Transactional(readOnly = true)
	@Query(value = "SELECT ordem_de_servico.*  FROM garantia INNER JOIN ordem_de_servico ON garantia.ordemdeservico_id = ordem_de_servico.id", nativeQuery = true)
	public Collection<OrdemDeServico> findOrdemGarantia();
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT ordem_de_servico.*  FROM ordem_de_servico where ordem_de_servico.pagamento ='Nao' and ordem_de_servico.statusordemdeservico_id=3", nativeQuery = true)
	public Collection<OrdemDeServico> findOrdemFechadasNaoPagas();
	
    @Transactional(readOnly = true)
	public Collection<OrdemDeServico> findByStatusOrdemDeServico(StatusOrdemDeServico statusOrdemDeServico);


}
