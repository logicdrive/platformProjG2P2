package bookGenerator.file.domain;

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
@Table(name = "App_File")
public class File extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long fileId;

	private String url;

    private Date createdDate;
    
    private Date updatedDate;


    public static FileRepository repository() {
        return BootApplication.applicationContext.getBean(
            FileRepository.class
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
