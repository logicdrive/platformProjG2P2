package [[SERVICE_INFO.PACKAGE_NAME]].domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "[[TEMPLATE.RES_PATH]]", path = "[[TEMPLATE.RES_PATH]]")
public interface [[TEMPLATE.NAME]]Repository
    extends PagingAndSortingRepository<[[TEMPLATE.NAME]], Long> {
}
