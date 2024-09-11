package jwlee.crudpractice.crud.controller;

import jakarta.validation.Valid;
import jwlee.crudpractice.crud.domain.user.User;
import jwlee.crudpractice.crud.model.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping
    public String home(Model model) {
        User user = User.builder()
                .id(1L)
                .name("이지누")
                .email("test@gmail.com")
                .build();
        model.addAttribute("user", user);

        return "index";
    }

    @PostMapping("/user")
    @ResponseBody
    public void   home(@Valid @RequestBody UserDto userDto) {

    }
}
