package belyaev.ai182.barbershop.repositories;

import belyaev.ai182.barbershop.entities.Appointment;
import belyaev.ai182.barbershop.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    List<Appointment> findAllByDate(String date);
    Appointment findByWorkerAndDateAndTime(Worker worker, String date, String time);
}

