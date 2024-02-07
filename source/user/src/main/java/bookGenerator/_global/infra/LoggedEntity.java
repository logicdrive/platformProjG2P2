package bookGenerator._global.infra;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import bookGenerator._global.logger.CustomLogger;
import bookGenerator._global.logger.CustomLoggerType;

public class LoggedEntity {

    @PrePersist
    public void onPrePersist() {
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
