package bookGenerator.file.domain;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
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

    public static File createWithObject(Object source) {
        File fileToCreate = (new File());

        BeanUtils.copyProperties(source, fileToCreate);
        fileToCreate.setFileId(fileToCreate.getId());
        fileToCreate.setId(null);
        
        return fileToCreate;
    }

    public File copyAllProperties(Object source) {
        BeanUtils.copyProperties(source, this, "id", "fileId");
        return this;
    }


    @PrePersist
    public void onPrePersist() {super.onPrePersist();}

    @PostPersist
    public void onPostPersist() {super.onPostPersist();}

    @PreUpdate
    public void onPreUpdate() {super.onPreUpdate();}

    @PostUpdate
    public void onPostUpdate() {super.onPostUpdate();}
    
    @PreRemove
    public void onPreRemove() {super.onPreRemove();}

    @PostRemove
    public void onPostRemove() {super.onPostRemove();}
}
