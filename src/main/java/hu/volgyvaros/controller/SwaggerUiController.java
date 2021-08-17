package hu.volgyvaros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SwaggerUiController {

    @RequestMapping({"/", "/swagger"})
    public String swaggerUi() {
        return "redirect:/swagger-ui.html";
    }

}
