package bookGenerator.likeHistory.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;


@RepositoryRestResource(collectionResourceRel = "likeHistories", path = "likeHistories")
public interface LikeHistoryRepository
    extends PagingAndSortingRepository<LikeHistory, Long> {
    List<LikeHistory> findByBookId(Long bookId);
    List<LikeHistory> findByUserId(Long userId);
}
