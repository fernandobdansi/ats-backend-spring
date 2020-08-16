package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.Cliente;
import edu.ifes.ci.si.les.ats.model.Orcamento;
import edu.ifes.ci.si.les.ats.model.OrcamentoServicoItem;
import edu.ifes.ci.si.les.ats.repositories.ClienteRepository;
import edu.ifes.ci.si.les.ats.repositories.OrcamentoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	public Orcamento findById(Integer id) {
		Orcamento obj = orcamentoRepository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Orcamento.class.getName());
		}
		return obj;
	}

	public Collection<Orcamento> findAll() {
		return orcamentoRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Orcamento insert(Orcamento obj) {
		try {
			if (verificarRegrasDeNegocio(obj)) {
				obj.setId(null);
				for (OrcamentoServicoItem item : obj.getOrcamentoServicoItem()) {
					item.setOrcamento(obj);
				}
				return orcamentoRepository.save(obj);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Orcamento update(Orcamento obj) {
		try {
			findById(obj.getId());
			for (OrcamentoServicoItem item : obj.getOrcamentoServicoItem()) {
				item.setOrcamento(obj);
			}
			return orcamentoRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void delete(Integer id) {
		findById(id);
		try {
			orcamentoRepository.deleteById(id);
			orcamentoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um OrdemDeServico!");
		}
	}

	public boolean verificarRegrasDeNegocio(Orcamento obj) {

		// Regra de Negócio 3: Cliente não pode ter Ordens não pagas
		Collection<Cliente> devedores = clienteRepository.findDevedores();
		boolean clienteDevedor = false;
		for (Cliente devedor : devedores) {
			if (devedor.getId() == obj.getDispositivo().getCliente().getId()) {
				clienteDevedor = true;
			}
		}
		if (clienteDevedor) {
			throw new BusinessRuleException("Este cliente deve Ordens de Serviço anteriores!");
		}

		if (!clienteDevedor) {
			return true;
		} else {
			return false;
		}
	}

}
