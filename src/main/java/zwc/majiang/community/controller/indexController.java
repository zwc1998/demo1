package zwc.majiang.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zwc.majiang.community.dto.PagunationDto;
import zwc.majiang.community.dto.QuestionDto;
import zwc.majiang.community.mapper.QuestionMapper;
import zwc.majiang.community.mapper.Usermapper;
import zwc.majiang.community.model.Question;
import zwc.majiang.community.model.User;
import zwc.majiang.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class indexController {
@Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "1") Integer page,
                        @RequestParam(name="size",defaultValue = "5") Integer size) {

        PagunationDto pagunation =questionService.list(page,size);
        model.addAttribute("pagunation",pagunation);

        return "index";
    }
}
