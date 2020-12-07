package belyaev.ai182.barbershop.controllers;

import belyaev.ai182.barbershop.entities.User;
import belyaev.ai182.barbershop.entities.Worker;
import belyaev.ai182.barbershop.services.UserService;
import belyaev.ai182.barbershop.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WorkerController {
    @Autowired
    private WorkerService service;
    @Autowired
    private UserService userService;

    @GetMapping("/work")
    public String worker(Model model) {
        model.addAttribute("worker", new Worker());
        return "work";
    }

    @PostMapping("/work")
    public String addWorker(@Valid @ModelAttribute("worker") Worker worker,
                            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "work";
        }
        service.addWorker(worker);
        userService.setWorker(worker.getEmail());
        return "redirect:/";
    }

}
