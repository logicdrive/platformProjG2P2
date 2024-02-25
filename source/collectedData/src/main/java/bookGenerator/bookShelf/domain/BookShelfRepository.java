package bookGenerator.bookShelf.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookShelfs", path = "bookShelfs")
public interface BookShelfRepository
    extends PagingAndSortingRepository<BookShelf, Long> {
    Optional<BookShelf> findByBookShelfId(Long bookShelfId);
    Page<BookShelf> findByCreaterIdAndTitleContainingIgnoreCaseOrderByCreatedDate(Long createrId, String title, Pageable pageable);
    Page<BookShelf> findByCreaterIdOrderByTitle(Long createrId, Pageable pageable);
    Page<BookShelf> findByIsSharedAndTitleContainingIgnoreCaseOrderByCreatedDateDesc(Boolean isShared, String title, Pageable pageable);
    Page<BookShelf> findByIsSharedAndCreaterIdOrderByCreatedDateDesc(Boolean isShared, Long createrId, Pageable pageable);
}
