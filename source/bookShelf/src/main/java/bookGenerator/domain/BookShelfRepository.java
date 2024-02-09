package bookGenerator.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookShelfs", path = "bookShelfs")
public interface BookShelfRepository
    extends PagingAndSortingRepository<BookShelf, Long> {
}
