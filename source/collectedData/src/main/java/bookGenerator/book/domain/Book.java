package bookGenerator.book.domain;

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
@Table(name = "App_Book")
public class Book extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long bookId;

	private Long createrId;

	private Long coverImageFileId;

	private String title;

	private Boolean isShared;

    private Date createdDate;
    
    private Date updatedDate;


    public static BookRepository repository() {
        return BootApplication.applicationContext.getBean(
            BookRepository.class
        );
    }

    public static Book createWithObject(Object source) {
        Book bookToCreate = (new Book());

        BeanUtils.copyProperties(source, bookToCreate);
        bookToCreate.setBookId(bookToCreate.getId());
        bookToCreate.setId(null);
        
        return bookToCreate;
    }

    public Book copyAllProperties(Object source) {
        BeanUtils.copyProperties(source, this, "id", "bookId");
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
