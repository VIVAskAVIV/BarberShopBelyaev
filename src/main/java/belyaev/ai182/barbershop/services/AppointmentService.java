package belyaev.ai182.barbershop.services;

import belyaev.ai182.barbershop.entities.Appointment;
import belyaev.ai182.barbershop.entities.Worker;
import belyaev.ai182.barbershop.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private AppointmentRepository repository;

    @Autowired
    public void setRepository(AppointmentRepository repository) {
        this.repository = repository;
    }

    public Appointment addAppointment(Appointment appointment) {
        return repository.save(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        repository.delete(appointment);
    }

    public List<Appointment> findAll() {
        return repository.findAll();
    }

    public List<Appointment> findByDate(String date) {
        return repository.findAllByDate(date);
    }
    public Appointment findByWorkerAndDateAndTime(Worker worker, String date, String time) {
        return repository.findByWorkerAndDateAndTime(worker, date, time);
    }
}
