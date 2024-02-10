package bookGenerator.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "indexes", path = "indexes")
public interface IndexRepository
    extends PagingAndSortingRepository<Index, Long> {
}
