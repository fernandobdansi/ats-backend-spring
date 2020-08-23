package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.Cliente;
import edu.ifes.ci.si.les.ats.model.OrdemDeServico;
import edu.ifes.ci.si.les.ats.model.OrdemServicoItem;
import edu.ifes.ci.si.les.ats.model.StatusOrdemDeServico;
import edu.ifes.ci.si.les.ats.repositories.ClienteRepository;
import edu.ifes.ci.si.les.ats.repositories.OrdemDeServicoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemDeServicoService {

	@Autowired
	private OrdemDeServicoRepository ordemServicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	public OrdemDeServico findById(Integer id) {
		OrdemDeServico obj = ordemServicoRepository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemDeServico.class.getName());
		}
		return obj;
	}

	public Collection<OrdemDeServico> findAll() {
		return ordemServicoRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public OrdemDeServico insert(OrdemDeServico obj) {
		try {
			if (verificarRegrasDeNegocio(obj)) {
				obj.setId(null);
				for (OrdemServicoItem item : obj.getOrdemServicosItem()) {
					item.setOrdemDeServico(obj);
				}
				return ordemServicoRepository.save(obj);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public OrdemDeServico update(OrdemDeServico obj) {
		try {
			findById(obj.getId());
			for (OrdemServicoItem item : obj.getOrdemServicosItem()) {
				item.setOrdemDeServico(obj);
			}
			return ordemServicoRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void delete(Integer id) {
		findById(id);
		try {
			ordemServicoRepository.deleteById(id);
			ordemServicoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um OrdemDeServico!");
		}
	}

	public boolean verificarRegrasDeNegocio(OrdemDeServico obj) {

		// Regra de Negócio 1: Cliente não pode ter Ordens não pagas
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
	
    public Collection<OrdemDeServico> findOrdemFechadasNaoPagas() {
        return ordemServicoRepository.findOrdemFechadasNaoPagas();
    }
    
    public Collection<OrdemDeServico> findByStatusOrdemDeServico(StatusOrdemDeServico statusOrdemDeServico) {
        return ordemServicoRepository.findByStatusOrdemDeServico(statusOrdemDeServico);
    }

}
