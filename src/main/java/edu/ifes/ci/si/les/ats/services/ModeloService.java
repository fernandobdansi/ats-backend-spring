package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import edu.ifes.ci.si.les.ats.model.Marca;
import edu.ifes.ci.si.les.ats.model.Modelo;
import edu.ifes.ci.si.les.ats.repositories.ModeloRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class ModeloService {

	@Autowired
	private ModeloRepository repository;

	public Modelo findById(Integer id) {
		Modelo obj = repository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Modelo.class.getName());
		}
		return obj;
	}

	public Collection<Modelo> findAll() {
		return repository.findAll();
	}
	
    public Collection<Modelo> findByMarca(Marca marca) {
        return repository.findByMarca(marca);
    }

	public Modelo insert(Modelo obj) {
		obj.setId(null);
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do não foi(foram) preenchido(s)");
		}
	}

	public Modelo update(Modelo obj) {
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
