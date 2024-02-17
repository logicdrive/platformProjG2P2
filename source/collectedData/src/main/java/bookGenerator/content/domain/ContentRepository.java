package bookGenerator.content.domain;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "contents", path = "contents")
public interface ContentRepository
    extends PagingAndSortingRepository<Content, Long> {
    Optional<Content> findByContentId(Long contentId);
}
