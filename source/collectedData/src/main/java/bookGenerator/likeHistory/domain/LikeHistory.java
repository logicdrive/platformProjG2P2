package bookGenerator.likeHistory.domain;

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
@Table(name = "App_LikeHistory")
public class LikeHistory extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long userId;

	private Long bookId;

    private Date createdDate;
    
    private Date updatedDate;


    public static LikeHistoryRepository repository() {
        return BootApplication.applicationContext.getBean(
            LikeHistoryRepository.class
        );
    }

    public static LikeHistory createWithObject(Object source) {
        LikeHistory likeHistoryToCreate = (new LikeHistory());

        BeanUtils.copyProperties(source, likeHistoryToCreate);
        likeHistoryToCreate.setId(null);
        
        return likeHistoryToCreate;
    }

    public LikeHistory copyAllProperties(Object source) {
        BeanUtils.copyProperties(source, this, "id");
        return this;
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


    @PostPersist
    public void onPostPersist() {super.onPostPersist();}

    @PostUpdate
    public void onPostUpdate() {super.onPostUpdate();}
    
    @PreRemove
    public void onPreRemove() {super.onPreRemove();}

    @PostRemove
    public void onPostRemove() {super.onPostRemove();}
}
