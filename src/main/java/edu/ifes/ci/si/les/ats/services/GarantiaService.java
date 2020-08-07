package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.Garantia;
import edu.ifes.ci.si.les.ats.repositories.GarantiaRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class GarantiaService {

	@Autowired
	private GarantiaRepository repository;

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
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do Garantia não foi(foram) preenchido(s)");
		}
	}

	public Garantia update(Garantia obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do Garantia não foi(foram) preenchido(s)");
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

}
