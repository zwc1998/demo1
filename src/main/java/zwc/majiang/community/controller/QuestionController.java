package zwc.majiang.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import zwc.majiang.community.dto.QuestionDto;
import zwc.majiang.community.mapper.QuestionMapper;
import zwc.majiang.community.service.QuestionService;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id,
                           Model model){
        QuestionDto questionDto =questionService.getById(id);
        model.addAttribute("question",questionDto);
        return "question";

    }}
