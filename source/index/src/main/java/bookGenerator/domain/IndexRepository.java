package bookGenerator.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "indexes", path = "indexes")
public interface IndexRepository
    extends PagingAndSortingRepository<Index, Long> {
    List<Index> findByBookId(Long bookId);
}
