package analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import analyzer.model.Condition;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
}
