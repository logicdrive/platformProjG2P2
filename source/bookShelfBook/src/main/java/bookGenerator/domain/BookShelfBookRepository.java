package bookGenerator.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookShelfBooks", path = "bookShelfBooks")
public interface BookShelfBookRepository
    extends PagingAndSortingRepository<BookShelfBook, Long> {
    Optional<BookShelfBook> findByBookId(Long bookId);
    List<BookShelfBook> findByBookShelfId(Long bookShelfId);
}
