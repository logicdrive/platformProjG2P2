package bookGenerator.recommenedBookToBook.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recommenedBookToBooks", path = "recommenedBookToBooks")
public interface RecommenedBookToBookRepository
    extends PagingAndSortingRepository<RecommenedBookToBook, Long> {
    Page<RecommenedBookToBook> findByBookIdOrderByPriority(Long bookId, Pageable pageable);
    void deleteByBookId(Long bookId);
    void deleteByRecommendedBookId(Long recommendedBookId);
}
