package jwlee.crudpractice.crud.controller;


import jwlee.crudpractice.crud.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/login")
    public String login(Model model) {

        model.addAttribute("userDto", new UserDto());
        return "login";
    }
}
