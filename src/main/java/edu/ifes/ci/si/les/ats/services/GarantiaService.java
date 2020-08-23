package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.Garantia;
import edu.ifes.ci.si.les.ats.model.OrdemDeServico;
import edu.ifes.ci.si.les.ats.model.StatusGarantia;
import edu.ifes.ci.si.les.ats.repositories.GarantiaRepository;
import edu.ifes.ci.si.les.ats.repositories.OrdemDeServicoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class GarantiaService {

	@Autowired
	private GarantiaRepository repository;
	@Autowired
	private OrdemDeServicoRepository ordemServicoRepository;

	public Garantia findById(Integer id) {
		Garantia obj = repository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Garantia.class.getName());
		}
		return obj;
	}

	public Collection<Garantia> findAll() {
		return repository.findAll();
	}

	public Garantia insert(Garantia obj) {
		obj.setId(null);
		try {
			if (verificarRegrasDeNegocio(obj)) {
				return repository.save(obj);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Garantia não foi(foram) preenchido(s)");
		}
		return null;
	}

	public Garantia update(Garantia obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do Garantia não foi(foram) preenchido(s)");
		}
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Garantia!");
		}
	}

	public boolean verificarRegrasDeNegocio(Garantia obj) {

		// Regra de Negócio 4: Cliente so Acionar a garantia uma unica vez para cada
		// ordem de serviço
		Collection<OrdemDeServico> ordeonsComFeedBack = ordemServicoRepository.findOrdemGarantia();
		boolean ordemFeedBack = false;
		for (OrdemDeServico ordensFeed : ordeonsComFeedBack) {
			if (ordensFeed.getId() == obj.getOrdemDeServico().getId()) {
				ordemFeedBack = true;
			}
		}
		if (ordemFeedBack) {
			throw new BusinessRuleException("Este cliente ja deu o FeedBack para esta Ordem!");
		}

		if (!ordemFeedBack) {
			return true;
		} else {
			return false;
		}
	}
	
    public Collection<Garantia> findByStatusGarantia(StatusGarantia statusGarantia) {
        return repository.findByStatusGarantia(statusGarantia);
    }


}
