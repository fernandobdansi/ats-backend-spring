package edu.ifes.ci.si.les.ats.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.ifes.ci.si.les.ats.model.FeedBack;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {
	
    @Transactional(readOnly = true)
	public Collection<FeedBack> findBySatisfacao(String satisfacao);

}
