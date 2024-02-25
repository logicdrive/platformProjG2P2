package bookGenerator.bookShelfBook.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookShelfBooks", path = "bookShelfBooks")
public interface BookShelfBookRepository
    extends PagingAndSortingRepository<BookShelfBook, Long> {
    Optional<BookShelfBook> findByBookShelfBookId(Long bookShelfBookId);
    Page<BookShelfBook> findByBookShelfIdOrderByCreatedDateDesc(Long bookShelfId, Pageable pageable);
}
