package edu.ifes.ci.si.les.ats.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifes.ci.si.les.ats.model.FeedBack;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {

}
