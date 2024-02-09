package bookGenerator.recommenedBookToBook.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recommenedBookToBooks", path = "recommenedBookToBooks")
public interface RecommenedBookToBookRepository
    extends PagingAndSortingRepository<RecommenedBookToBook, Long> {
}
