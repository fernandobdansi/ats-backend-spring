package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.OrdemDeServico;
import edu.ifes.ci.si.les.ats.repositories.OrdemDeServicoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemDeServicoService {

	@Autowired
	private OrdemDeServicoRepository repository;

	public OrdemDeServico findById(Integer id) {
		OrdemDeServico obj = repository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemDeServico.class.getName());
		}
		return obj;
	}

	public Collection<OrdemDeServico> findAll() {
		return repository.findAll();
	}

	public OrdemDeServico insert(OrdemDeServico obj) {
		obj.setId(null);
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
	}

	public OrdemDeServico update(OrdemDeServico obj) {
		findById(obj.getId());
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um OrdemDeServico!");
		}
	}

}
