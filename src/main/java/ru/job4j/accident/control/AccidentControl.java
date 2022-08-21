package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AccidentControl {
    private final AccidentService service;

    public AccidentControl(AccidentService service) {
        this.service = service;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", service.getTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", service.findById(id));
        model.addAttribute("types", service.getTypes());
        model.addAttribute("rules", service.getRules());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int id, HttpServletRequest req) {
        var type = service.getTypes().get(id - 1);
        accident.setType(type);
        var rules = service.getRules();
        String[] ids = req.getParameterValues("rIds");
        for (String ruleId : ids) {
            accident.getRules().add(rules.get(Integer.parseInt(ruleId) - 1));
        }
        service.save(accident);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int typeId, HttpServletRequest req) {
        var type = service.getTypes().get(typeId - 1);
        accident.setType(type);
        var rules = service.getRules();
        String[] ids = req.getParameterValues("rIds");
        for (String ruleId : ids) {
            accident.getRules().add(rules.get(Integer.parseInt(ruleId) - 1));
        }
        service.edit(accident);
        return "redirect:/";
    }
}
