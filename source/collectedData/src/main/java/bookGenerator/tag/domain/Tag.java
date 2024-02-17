package bookGenerator.tag.domain;

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
@Table(name = "App_Tag")
public class Tag extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long tagId;

	private Long bookId;

	private String name;

    private Date createdDate;
    
    private Date updatedDate;


    public static TagRepository repository() {
        return BootApplication.applicationContext.getBean(
            TagRepository.class
        );
    }

    public static Tag createWithObject(Object source) {
        Tag tagToCreate = (new Tag());

        BeanUtils.copyProperties(source, tagToCreate);
        tagToCreate.setTagId(tagToCreate.getId());
        tagToCreate.setId(null);
        
        return tagToCreate;
    }

    public Tag copyAllProperties(Object source) {
        BeanUtils.copyProperties(source, this, "id", "tagId");
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
