package kz.iitu.library.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "cars")
@ApiModel(description = "Details about the car")
public class Car {
    @ApiModelProperty(notes = "This car Id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "This car title")
    private String title;

    @ApiModelProperty(notes = "This car model")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "car_model",
            joinColumns = {@JoinColumn(name = "car_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "model_id", referencedColumnName = "id")})
    private Model model;


    @ApiModelProperty(notes = "This car user")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ApiModelProperty(notes = "This car status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Car(String title) {
        this.title = title;
    }

}
