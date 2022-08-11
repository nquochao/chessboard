package oliviaproject.hibernate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
 @Table(name = "author")
 public class Author {

     @Id
     @GeneratedValue
     private Long id;

     @Column(name = "first_name")
     private String firstName;

     @Column(name = "last_name")
     private String lastName;

     //Getter and setters omitted for brevity
 }