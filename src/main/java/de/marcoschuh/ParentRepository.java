package de.marcoschuh;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import de.marcoschuh.entities.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long>, QueryDslPredicateExecutor<Parent> {
	
	public List<Parent> findByName(String name);
	
}
