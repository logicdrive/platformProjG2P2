package bookGenerator.problem.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "problems", path = "problems")
public interface ProblemRepository
    extends PagingAndSortingRepository<Problem, Long> {
    Optional<Problem> findByProblemId(Long problemId);
    Page<Problem> findByIndexIdOrderByPriority(Long indexId, Pageable pageable);
}
