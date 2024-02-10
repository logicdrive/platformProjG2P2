package bookGenerator.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface TagRepository
    extends PagingAndSortingRepository<Tag, Long> {
    List<Tag> findByBookId(Long bookId);
}
