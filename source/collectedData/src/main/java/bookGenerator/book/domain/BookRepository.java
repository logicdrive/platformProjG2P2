package bookGenerator.book.domain;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository
    extends PagingAndSortingRepository<Book, Long> {
    Optional<Book> findByBookId(Long bookId);
    Optional<Book> findByCoverImageFileId(Long coverImageFileId);
    Page<Book> findByCreaterIdOrderByCreatedDateDesc(Long createrId, Pageable pageable);
    Page<Book> findByIsSharedOrderByCreatedDateDesc(Boolean isShared, Pageable pageable);
    Page<Book> findByIsSharedAndTitleContainingIgnoreCaseOrderByCreatedDateDesc(Boolean isShared, String title, Pageable pageable);
    Page<Book> findByIsSharedAndCreaterIdOrderByCreatedDateDesc(Boolean isShared, Long createrId, Pageable pageable);
    Page<Book> findByCreaterId(Long createrId, Pageable pageable);
}