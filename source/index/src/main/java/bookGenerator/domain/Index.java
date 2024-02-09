package bookGenerator.domain;

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

import bookGenerator.BootApplication;
import bookGenerator._global.infra.LoggedEntity;

@Data
@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "App_Index")
public class Index extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long bookId;

	private String name;

	private Long priority;

    private Date createdDate;
    
    private Date updatedDate;


    public static IndexRepository repository() {
        return BootApplication.applicationContext.getBean(
            IndexRepository.class
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
