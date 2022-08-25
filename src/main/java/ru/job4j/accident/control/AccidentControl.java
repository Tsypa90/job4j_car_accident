package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.RuleService;
import ru.job4j.accident.service.TypeService;

import java.util.List;

@Controller
public class AccidentControl {
    private final AccidentService accidentService;
    private final RuleService ruleService;
    private final TypeService typeService;

    public AccidentControl(AccidentService accidentService, RuleService ruleService, TypeService typeService) {
        this.accidentService = accidentService;
        this.ruleService = ruleService;
        this.typeService = typeService;
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "accident/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id));
        model.addAttribute("types", typeService.getTypes());
        model.addAttribute("rules", ruleService.getRules());
        return "accident/edit";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int typeId,
                       @RequestParam("rule.id") List<Integer> rulesId) {
        accident.setType(typeService.findTypeById(typeId));
        rulesId.forEach(s -> accident.ruleAdd(ruleService.findRuleById(s)));
        accidentService.save(accident);
        return "redirect:/";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Accident accident,
                       @RequestParam("type.id") int typeId,
                       @RequestParam("rule.id") List<Integer> rulesId) {
        accident.setType(typeService.findTypeById(typeId));
        rulesId.forEach(s -> accident.ruleAdd(ruleService.findRuleById(s)));
        accidentService.edit(accident);
        return "redirect:/";
    }
}
