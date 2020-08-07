package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.Orcamento;
import edu.ifes.ci.si.les.ats.repositories.OrcamentoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository repository;

	public Orcamento findById(Integer id) {
		Orcamento obj = repository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Orcamento.class.getName());
		}
		return obj;
	}

	public Collection<Orcamento> findAll() {
		return repository.findAll();
	}

	public Orcamento insert(Orcamento obj) {
		obj.setId(null);
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do Orcamento não foi(foram) preenchido(s)");
		}
	}

	public Orcamento update(Orcamento obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do Orcamento não foi(foram) preenchido(s)");
		}
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um Orcamento!");
		}
	}

}
