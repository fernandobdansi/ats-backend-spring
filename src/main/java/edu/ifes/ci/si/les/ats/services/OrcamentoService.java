package edu.ifes.ci.si.les.ats.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.Orcamento;
import edu.ifes.ci.si.les.ats.model.OrcamentoServicoItem;
import edu.ifes.ci.si.les.ats.repositories.OrcamentoRepository;
import edu.ifes.ci.si.les.ats.services.exceptions.DataIntegrityException;
import edu.ifes.ci.si.les.ats.services.exceptions.ObjectNotFoundException;

@Service
public class OrcamentoService {

	@Autowired
	private OrcamentoRepository orcamentoRepository;

	public Orcamento findById(Integer id) {
		Orcamento obj = orcamentoRepository.findById(id).get();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Orcamento.class.getName());
		}
		return obj;
	}

	public Collection<Orcamento> findAll() {
		return orcamentoRepository.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Orcamento insert(Orcamento obj) {
		try {
			obj.setId(null);
			for (OrcamentoServicoItem item : obj.getOrcamentoServicoItem()) {
				item.setOrcamento(obj);
			}
			return orcamentoRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Orcamento update(Orcamento obj) {
		try {
			findById(obj.getId());
			for (OrcamentoServicoItem item : obj.getOrcamentoServicoItem()) {
				item.setOrcamento(obj);
			}
			return orcamentoRepository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Campo(s) obrigatório(s) do OrdemDeServico não foi(foram) preenchido(s)");
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void delete(Integer id) {
		findById(id);
		try {
			orcamentoRepository.deleteById(id);
			orcamentoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um OrdemDeServico!");
		}
	}

}
