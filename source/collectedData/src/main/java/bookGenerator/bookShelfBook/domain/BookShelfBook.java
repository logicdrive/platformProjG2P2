package bookGenerator.bookShelfBook.domain;

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
@Table(name = "App_BookShelfBook")
public class BookShelfBook extends LoggedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private Long bookShelfBookId;

	private Long bookId;

    private Date createdDate;
    
    private Date updatedDate;


    public static BookShelfBookRepository repository() {
        return BootApplication.applicationContext.getBean(
            BookShelfBookRepository.class
        );
    }

    public static BookShelfBook createWithObject(Object source) {
        BookShelfBook bookShelfBookToCreate = (new BookShelfBook());

        BeanUtils.copyProperties(source, bookShelfBookToCreate);
        bookShelfBookToCreate.setBookShelfBookId(bookShelfBookToCreate.getId());
        bookShelfBookToCreate.setId(null);
        
        return bookShelfBookToCreate;
    }

    public BookShelfBook copyAllProperties(Object source) {
        BeanUtils.copyProperties(source, this, "id", "bookShelfBookId");
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
