package analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import analyzer.model.Action;

public interface ActionRepository extends JpaRepository<Action, Long> {
}
