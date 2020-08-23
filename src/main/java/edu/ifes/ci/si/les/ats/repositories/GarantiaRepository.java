package edu.ifes.ci.si.les.ats.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.Garantia;
import edu.ifes.ci.si.les.ats.model.StatusGarantia;

@Repository
public interface GarantiaRepository extends JpaRepository<Garantia, Integer> {

	@Transactional(readOnly = true)
	public Collection<Garantia> findByStatusGarantia(StatusGarantia statusGarantia);

}
