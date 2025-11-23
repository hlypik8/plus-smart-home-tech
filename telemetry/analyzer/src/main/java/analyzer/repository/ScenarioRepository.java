package analyzer.repository;

import analyzer.model.Scenario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {

    @EntityGraph(attributePaths = {"conditions", "actions"})
    @Transactional(readOnly = true) // опционально, но полезно для чтения
    List<Scenario> findByHubId(String hubId);

    void deleteByName(String name);
}
