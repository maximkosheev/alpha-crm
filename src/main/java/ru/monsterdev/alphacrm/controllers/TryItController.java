package ru.monsterdev.alphacrm.controllers;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.monsterdev.alphacrm.model.TryitForm;
import ru.monsterdev.alphacrm.services.ManagerService;

@Controller
@RequestMapping("/tryit")
@RequiredArgsConstructor
public class TryItController {

  private final ManagerService managerService;

  @Autowired
  @Qualifier("tryItFormValidator")
  private Validator tryItFormValidator;

  @Value("${google.reCAPTCHA.site-key}")
  private String siteKey;

  @InitBinder
  private void initBinder(WebDataBinder dataBinder) {
    dataBinder.setValidator(tryItFormValidator);
  }

  @GetMapping()
  public String tryit(Model model) {
    TryitForm form = new TryitForm();
    model.addAttribute("form", form);
    model.addAttribute("siteKey", siteKey);
    return "tryit";
  }

  @PostMapping()
  public String createDemo(@Valid @ModelAttribute("form") TryitForm form,
      BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      form.setCaptcha(null);
      model.addAttribute("siteKey", siteKey);
      return "tryit";
    } else {
      try {
        managerService.createDemoAccount(form);
        return "redirect:/tryit/success";
      } catch (Exception ex) {
        return "redirect:/tryit/failed";
      }
    }
  }

  @GetMapping("/success")
  public String showSuccess() {
    return "tryit-success";
  }

  @GetMapping("/failed")
  public String showFailed() {
    return "tryit-failed";
  }
}
