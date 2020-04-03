package kz.iitu.library.models;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Book> books;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
    public void notify(Book book){
        System.out.println("Книга " + book.getTitle() + "теперь у вас" );
    }
}