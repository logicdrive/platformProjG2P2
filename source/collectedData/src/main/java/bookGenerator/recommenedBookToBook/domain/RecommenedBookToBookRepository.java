package bookGenerator.recommenedBookToBook.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recommenedBookToBooks", path = "recommenedBookToBooks")
public interface RecommenedBookToBookRepository
    extends PagingAndSortingRepository<RecommenedBookToBook, Long> {
    List<RecommenedBookToBook> findByBookIdOrderByPriority(Long bookId);
    void deleteByBookId(Long bookId);
}
