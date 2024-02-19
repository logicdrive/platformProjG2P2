package bookGenerator.recommendBookToUser.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recommendBookToUsers", path = "recommendBookToUsers")
public interface RecommendBookToUserRepository
    extends PagingAndSortingRepository<RecommendBookToUser, Long> {
    List<RecommendBookToUser> findByUserIdOrderByPriority(Long userId);
    void deleteByUserId(Long userId);
    void deleteByRecommendedBookId(Long recommendedBookId);
}
