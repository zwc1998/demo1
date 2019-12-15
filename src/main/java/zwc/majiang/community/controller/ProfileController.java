package zwc.majiang.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import zwc.majiang.community.dto.PagunationDto;
import zwc.majiang.community.mapper.Usermapper;
import zwc.majiang.community.model.User;
import zwc.majiang.community.service.QuestionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller


public class ProfileController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/profile/{action}")
    public String profile(HttpServletRequest request,
                           @PathVariable(name="action") String action, Model model,
                          @RequestParam(name="page",defaultValue = "1") Integer page,
                          @RequestParam(name="size",defaultValue = "5")  Integer size){
        User user =(User) request.getSession().getAttribute("user");
    if (user ==null){
    return "redirect:/";
}
        if ("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");

        }else if ("replies".equals(action)){
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        PagunationDto pagunationDto =questionService.list(user.getId(),page,size);
        model.addAttribute("pagunation",pagunationDto);
        return "profile";
    }

}
