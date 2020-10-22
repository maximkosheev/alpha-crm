package ru.monsterdev.alphacrm.controllers;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.monsterdev.alphacrm.model.TryitForm;
import ru.monsterdev.alphacrm.services.ManagerService;

@Controller
@RequestMapping("/tryit")
@RequiredArgsConstructor
public class TryItController {

  private final ManagerService managerService;

  @GetMapping()
  public String tryit(TryitForm tryitForm) {
    return "tryit";
  }

  @PostMapping()
  public String createDemo(@Valid TryitForm tryitForm, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "tryit";
    } else {
      managerService.createDemoAccount(tryitForm);
      return "redirect:/";
    }
  }
}
