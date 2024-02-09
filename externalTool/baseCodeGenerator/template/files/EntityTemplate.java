package [[SERVICE_INFO.PACKAGE_NAME]].domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import [[SERVICE_INFO.PACKAGE_NAME]].BootApplication;
import [[SERVICE_INFO.PACKAGE_NAME]]._global.infra.LoggedEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "App_[[TEMPLATE.NAME]]")
public class [[TEMPLATE.NAME]] extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

[[TEMPLATE.ATTRIBUTES]]
    private Date createdDate;
    
    private Date updatedDate;


    public static [[TEMPLATE.NAME]]Repository repository() {
        return BootApplication.applicationContext.getBean(
            [[TEMPLATE.NAME]]Repository.class
        );
    }


    @PrePersist
    public void onPrePersist() {
        this.createdDate = new Date();
        this.updatedDate = new Date();

        super.onPrePersist();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedDate = new Date();

        super.onPreUpdate();
    }
}
