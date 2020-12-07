package belyaev.ai182.barbershop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "worker")
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer workerId;
    @Size(min = 2, max= 50, message = "Имя неправильно указано")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max= 50, message = "Фамилия неправильно указана")
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 2, max= 50, message = "Отчество неправильно указано")
    @Column(name = "patronymic")
    private String patronymic;

    @Pattern(regexp = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$",
            message = "Неправильный email адресс")
    private String email;

    @Pattern(regexp = "[0-9]{10}",
            message = "Неправильный номер телефона")
    private String phone;

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    public String getName(){
        return getLastName() + " " + getFirstName() + " " + getPatronymic();
    }
}
