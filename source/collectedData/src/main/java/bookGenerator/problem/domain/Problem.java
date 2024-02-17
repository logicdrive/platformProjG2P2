package bookGenerator.problem.domain;

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
@Table(name = "App_Problem")
public class Problem extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long problemId;

	private Long indexId;

	private String content;

	private String answer;

	private Long priority;

    private Date createdDate;
    
    private Date updatedDate;


    public static ProblemRepository repository() {
        return BootApplication.applicationContext.getBean(
            ProblemRepository.class
        );
    }

    public static Problem createWithObject(Object source) {
        Problem problemToCreate = (new Problem());

        BeanUtils.copyProperties(source, problemToCreate);
        problemToCreate.setProblemId(problemToCreate.getId());
        problemToCreate.setId(null);
        
        return problemToCreate;
    }

    public Problem copyAllProperties(Object source) {
        BeanUtils.copyProperties(source, this, "id", "problemId");
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
