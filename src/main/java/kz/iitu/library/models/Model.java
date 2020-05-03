package kz.iitu.library.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "model")
public class Model{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "This car type")
    private String name;


    @OneToOne(mappedBy = "model", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Car car;

    public Model(String type){
        this.name = type;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", type='" + name + '\'' +
                '}';
    }
}
