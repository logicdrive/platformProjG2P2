package bookGenerator.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "indexs", path = "indexs")
public interface IndexRepository
    extends PagingAndSortingRepository<Index, Long> {
}
