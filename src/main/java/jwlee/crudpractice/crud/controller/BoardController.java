package jwlee.crudpractice.crud.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("/sassa")
    public String board() {

        return "index1111";
    }
}
