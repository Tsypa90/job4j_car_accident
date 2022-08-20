package ru.job4j.accident;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexControl {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("students", List.of("Pavel", "Olga", "Igor", "Andrey"));
        return "index";
    }

}
