package belyaev.ai182.barbershop.controllers;

import belyaev.ai182.barbershop.entities.Appointment;
import belyaev.ai182.barbershop.entities.Worker;
import belyaev.ai182.barbershop.services.AppointmentService;
import belyaev.ai182.barbershop.services.UserService;
import belyaev.ai182.barbershop.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private WorkerService workerService;

    @Autowired
    private UserService userService;

    @GetMapping("/appointment")
    public String appointment() {
        return "/appointment";
    }

    @GetMapping("add")
    public String getAppointment(@RequestParam("date") String date,
                                 Model model) {
        model.addAttribute("workers", workerService.findAll());
        model.addAttribute("appointment", appointmentService.findByDate(date));
        return "add";
    }

    @PostMapping("/appointment_add")
    public String addAppointment(@RequestParam(value = "date") String date,
                                 @RequestParam(value = "time") String time,
                                 @RequestParam(value = "worker") Worker worker,
                                 Authentication authentication,
                                 RedirectAttributes redirectAttributes) {
        appointmentService.addAppointment(new Appointment(worker, date, time,
                userService.findByEmail(authentication.getName())));
        redirectAttributes.addAttribute("date", date);
        return "redirect:/add";
    }

    @PostMapping("/appointment_delete")
    public String deleteAppointment(@RequestParam(value = "date") String date,
                                    @RequestParam(value = "time") String time,
                                    @RequestParam(value = "worker") Worker worker,
                                    RedirectAttributes redirectAttributes){
        appointmentService.deleteAppointment(appointmentService.findByWorkerAndDateAndTime(worker, date, time));
        redirectAttributes.addAttribute("date", date);
        return "redirect:/add";
    }
}
