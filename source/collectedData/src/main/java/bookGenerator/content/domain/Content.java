package bookGenerator.content.domain;

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
@Table(name = "App_Content")
public class Content extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long contentId;

	private Long iundexId;

	private String content;

    private Date createdDate;
    
    private Date updatedDate;


    public static ContentRepository repository() {
        return BootApplication.applicationContext.getBean(
            ContentRepository.class
        );
    }

    public static Content createWithObject(Object source) {
        Content contentToCreate = (new Content());

        BeanUtils.copyProperties(source, contentToCreate);
        contentToCreate.setContentId(contentToCreate.getId());
        contentToCreate.setId(null);
        
        return contentToCreate;
    }

    public Content copyAllProperties(Object source) {
        BeanUtils.copyProperties(source, this, "id", "contentId");
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
