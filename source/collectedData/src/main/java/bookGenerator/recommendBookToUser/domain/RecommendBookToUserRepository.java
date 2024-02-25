package bookGenerator.recommendBookToUser.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recommendBookToUsers", path = "recommendBookToUsers")
public interface RecommendBookToUserRepository
    extends PagingAndSortingRepository<RecommendBookToUser, Long> {
    Page<RecommendBookToUser> findByUserIdOrderByPriority(Long userId, Pageable pageable);
    void deleteByUserId(Long userId);
    void deleteByRecommendedBookId(Long recommendedBookId);
}
