package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.FeedBack;
import edu.ifes.ci.si.les.ats.repositories.FeedBackRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class FeedBackService {

	@Autowired
	private FeedBackRepository repository;

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
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do FeedBack não foi(foram) preenchido(s)");
		}
	}

	public FeedBack update(FeedBack obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do FeedBack não foi(foram) preenchido(s)");
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

}


 


