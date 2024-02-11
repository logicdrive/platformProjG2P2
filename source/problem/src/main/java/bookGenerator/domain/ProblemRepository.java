package bookGenerator.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "problems", path = "problems")
public interface ProblemRepository
    extends PagingAndSortingRepository<Problem, Long> {
    List<Problem> findByIndexId(Long indexId);
}
