package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.StatusOrdemDeServico;
import edu.ifes.ci.si.les.ats.repositories.StatusOrdemDeServicoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class StatusOrdemDeServicoService {

	@Autowired
	private StatusOrdemDeServicoRepository repository;

	public StatusOrdemDeServico findById(Integer id) {
		StatusOrdemDeServico obj = repository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + StatusOrdemDeServico.class.getName());
		}
		return obj;
	}

	public Collection<StatusOrdemDeServico> findAll() {
		return repository.findAll();
	}

	public StatusOrdemDeServico insert(StatusOrdemDeServico obj) {
		obj.setId(null);
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do não foi(foram) preenchido(s)");
		}
	}

	public StatusOrdemDeServico update(StatusOrdemDeServico obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) não foi(foram) preenchido(s)");
		}
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um item associado a outros!");
		}
	}

}
