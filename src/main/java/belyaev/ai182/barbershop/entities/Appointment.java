package belyaev.ai182.barbershop.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
@Setter
@NoArgsConstructor
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer appointmentId;

    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Appointment(Worker worker, String date, String time, User user) {
        this.worker = worker;
        this.date = date;
        this.time = time;
        this.user = user;
    }

    public boolean equals(String time, Integer worker) {
        return (this.time.equals(time) && this.worker.getWorkerId().equals(worker));
    }

}
