package bookGenerator.recommendBookToUser.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "recommendBookToUsers", path = "recommendBookToUsers")
public interface RecommendBookToUserRepository
    extends PagingAndSortingRepository<RecommendBookToUser, Long> {
}
