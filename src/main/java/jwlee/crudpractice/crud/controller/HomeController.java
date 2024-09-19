package jwlee.crudpractice.crud.controller;

import jakarta.validation.Valid;
import jwlee.crudpractice.crud.model.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping
    public String home() {

        return "index";
    }

    @PostMapping("/user")
    @ResponseBody
    public void   home(@Valid @RequestBody UserDto userDto) {

    }
}
