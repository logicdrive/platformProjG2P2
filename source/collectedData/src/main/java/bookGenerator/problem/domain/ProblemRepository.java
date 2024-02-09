package bookGenerator.problem.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "problems", path = "problems")
public interface ProblemRepository
    extends PagingAndSortingRepository<Problem, Long> {
}
