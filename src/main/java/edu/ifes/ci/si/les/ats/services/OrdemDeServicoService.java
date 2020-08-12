package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.OrdemDeServico;
import edu.ifes.ci.si.les.ats.model.OrdemServicoItem;
import edu.ifes.ci.si.les.ats.repositories.OrdemDeServicoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemDeServicoService {

	@Autowired
	private OrdemDeServicoRepository ordemServicoRepository;

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
			obj.setId(null);
			for (OrdemServicoItem item : obj.getOrdemServicosItem()) {
				item.setOrdemDeServico(obj);
			}
			return ordemServicoRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
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

}
