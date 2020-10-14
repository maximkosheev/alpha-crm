package ru.monsterdev.alphacrm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.monsterdev.alphacrm.model.TryitForm;

@Controller
@RequestMapping
public class IndexController {

  @GetMapping(value = "/tryit")
  public String tryit(Model model) {
    TryitForm form = new TryitForm();
    model.addAttribute("model", form);
    return "tryit";
  }
}
