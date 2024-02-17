package bookGenerator.tag.domain;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
public interface TagRepository
    extends PagingAndSortingRepository<Tag, Long> {
    Optional<Tag> findByTagId(Long tagId);
}
