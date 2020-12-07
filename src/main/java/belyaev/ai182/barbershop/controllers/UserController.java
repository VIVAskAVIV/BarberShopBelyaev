package belyaev.ai182.barbershop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
