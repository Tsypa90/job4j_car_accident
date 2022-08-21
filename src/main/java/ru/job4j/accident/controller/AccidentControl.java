package ru.job4j.accident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        service.save(accident);
        return "redirect:/index";
    }

    @GetMapping("/index/{accidentId}")
    public String edit(@PathVariable("accidentId") int id, Model model) {
        model.addAttribute("accident", service.getById(id));
        return "edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        service.update(accident);
        return "redirect:/index";
    }
}
