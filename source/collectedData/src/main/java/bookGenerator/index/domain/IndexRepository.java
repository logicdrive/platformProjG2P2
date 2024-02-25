package bookGenerator.index.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "indexes", path = "indexes")
public interface IndexRepository
    extends PagingAndSortingRepository<Index, Long> {
    Optional<Index> findByIndexId(Long indexId);
    Page<Index> findByBookIdOrderByPriority(Long bookId, Pageable pageable);
}
