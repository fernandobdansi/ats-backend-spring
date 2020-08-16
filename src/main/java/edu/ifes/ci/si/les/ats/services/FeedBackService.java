package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.FeedBack;
import edu.ifes.ci.si.les.ats.model.OrdemDeServico;
import edu.ifes.ci.si.les.ats.repositories.FeedBackRepository;
import edu.ifes.ci.si.les.ats.repositories.OrdemDeServicoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.BusinessRuleException;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class FeedBackService {

	@Autowired
	private FeedBackRepository repository;
	@Autowired
	private OrdemDeServicoRepository ordemServicoRepository;

	public FeedBack findById(Integer id) {
		FeedBack obj = repository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + FeedBack.class.getName());
		}
		return obj;
	}

	public Collection<FeedBack> findAll() {
		return repository.findAll();
	}

	public FeedBack insert(FeedBack obj) {
		obj.setId(null);
		try {
			if (verificarRegrasDeNegocio(obj)) {
				return repository.save(obj);
			}
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do FeedBack não foi(foram) preenchido(s)");
		}
		return null;
	}

	public FeedBack update(FeedBack obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do FeedBack não foi(foram) preenchido(s)");
		}
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um FeedBack!");
		}
	}

	public boolean verificarRegrasDeNegocio(FeedBack obj) {

		// Regra de Negócio 3: Cliente so pode dar o feedBack uma Vez para cada Ordem
		Collection<OrdemDeServico> ordeonsComFeedBack = ordemServicoRepository.findOrdemFeedBack();
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

}
