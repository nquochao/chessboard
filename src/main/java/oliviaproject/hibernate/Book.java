package oliviaproject.hibernate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "book",
    uniqueConstraints =  @UniqueConstraint(
        name = "uk_book_title_author",
        columnNames = {
            "title",
            "author_id"
        }
   )
)
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "author_id",
        foreignKey = @ForeignKey(name = "fk_book_author_id")
   )
    private Author author;

    //Getter and setters omitted for brevity
}