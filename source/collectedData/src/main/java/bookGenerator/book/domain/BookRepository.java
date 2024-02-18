package bookGenerator.book.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository
    extends PagingAndSortingRepository<Book, Long> {
    Optional<Book> findByBookId(Long bookId);
    List<Book> findByCreaterIdOrderByCreatedDateDesc(Long createrId);
    List<Book> findByIsSharedOrderByCreatedDateDesc(Boolean isShared);
    List<Book> findByIsSharedAndTitleContainingIgnoreCaseOrderByCreatedDateDesc(Boolean isShared, String title);
    List<Book> findByIsSharedAndCreaterIdOrderByCreatedDateDesc(Boolean isShared, Long createrId);
}