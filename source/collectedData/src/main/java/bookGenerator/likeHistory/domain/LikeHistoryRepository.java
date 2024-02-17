package bookGenerator.likeHistory.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "likeHistories", path = "likeHistories")
public interface LikeHistoryRepository
    extends PagingAndSortingRepository<LikeHistory, Long> {
}
