package bookGenerator.bookShelfBook.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookShelfBooks", path = "bookShelfBooks")
public interface BookShelfBookRepository
    extends PagingAndSortingRepository<BookShelfBook, Long> {
}
