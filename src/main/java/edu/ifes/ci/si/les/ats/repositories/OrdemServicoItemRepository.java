package edu.ifes.ci.si.les.ats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.ats.model.OrdemServicoItem;
import edu.ifes.ci.si.les.ats.model.OrdemServicoItemPK;

@Repository
public interface OrdemServicoItemRepository extends JpaRepository<OrdemServicoItem, OrdemServicoItemPK> {

}
