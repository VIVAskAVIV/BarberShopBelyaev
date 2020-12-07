package belyaev.ai182.barbershop;

import belyaev.ai182.barbershop.entities.Appointment;

import java.util.List;

public class FindAnyAppointment {
    public static final Appointment findAnyMatch(List<Appointment> appointments, String time, Integer id) {

        if (appointments.stream().anyMatch(e -> e.equals(time, id))){
            return appointments.stream().filter(e -> e.equals(time, id)).findFirst().get();
       } else {
           return null;
       }
    }
}
