package bookGenerator.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "App_User")
public class User {    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String name;

    private String role;

    private Date createdDate;
    
    private Date updatedDate;


    @PrePersist
    public void onPrePersist() {
        this.createdDate = new Date();
        this.updatedDate = new Date();


        CustomLogger.debugObject(
            CustomLoggerType.EFFECT,
            String.format("Try to create %s by using JPA", this.getClass().getSimpleName()),
            this
        );
    }

    @PostPersist
    public void onPostPersist() {
        CustomLogger.debugObject(
            CustomLoggerType.EFFECT,
            String.format("%s is created by using JPA", this.getClass().getSimpleName()),
            this
        );
    }


    @PreUpdate
    public void onPreUpdate() {
        this.updatedDate = new Date();
        
        CustomLogger.debugObject(
            CustomLoggerType.EFFECT,
            String.format("Try to update %s by using JPA", this.getClass().getSimpleName()),
            this
        );
    }

    @PostUpdate
    public void onPostUpdate() {
        CustomLogger.debugObject(
            CustomLoggerType.EFFECT,
            String.format("%s is updated by using JPA", this.getClass().getSimpleName()),
            this
        );
    }


    @PreRemove
    public void onPreRemove() {
        CustomLogger.debugObject(
            CustomLoggerType.EFFECT,
            String.format("Try to delete %s by using JPA", this.getClass().getSimpleName()),
            this
        );
    }

    @PostRemove
    public void onPostRemove() {
        CustomLogger.debugObject(
            CustomLoggerType.EFFECT,
            String.format("%s is deleted by using JPA", this.getClass().getSimpleName()),
            this
        );
    }
}
