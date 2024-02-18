package bookGenerator.bookShelf.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "bookShelfs", path = "bookShelfs")
public interface BookShelfRepository
    extends PagingAndSortingRepository<BookShelf, Long> {
    Optional<BookShelf> findByBookShelfId(Long bookShelfId);
    List<BookShelf> findByCreaterIdOrderByTitle(Long createrId);
    List<BookShelf> findByIsSharedAndTitleContainingIgnoreCaseOrderByCreatedDateDesc(Boolean isShared, String title);
    List<BookShelf> findByIsSharedAndCreaterIdOrderByCreatedDateDesc(Boolean isShared, Long createrId);
}
