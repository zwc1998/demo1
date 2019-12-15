package zwc.majiang.community.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zwc.majiang.community.Eception.CustomizeException;
import zwc.majiang.community.dto.PagunationDto;
import zwc.majiang.community.dto.QuestionDto;
import zwc.majiang.community.mapper.QuestionMapper;
import zwc.majiang.community.mapper.Usermapper;
import zwc.majiang.community.model.Question;
import zwc.majiang.community.model.User;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private Usermapper usermapper;
    public PagunationDto list(Integer page, Integer size){
        PagunationDto pagunationDto = new PagunationDto();
        Integer totalPage;
        Integer totalCount = questionMapper.count();
        if (totalCount %size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }

        pagunationDto.setpagunation(totalPage,page);
        //size*(page-1)
        Integer offset =size *(page-1);
        List<Question> questions=questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList =new ArrayList<>();
        PagunationDto pagunationDto1 = new PagunationDto();
        for (Question question : questions){
            User user =usermapper.findById(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
       pagunationDto.setQuestions(questionDtoList);
        return pagunationDto;
}
    public  PagunationDto list(Integer userId, Integer page, Integer size) {
        PagunationDto pagunationDto = new PagunationDto();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);
        if (totalCount %size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }
        if (page<1){
            page=1;
        }
        if (page>totalPage){
            page=totalPage;
        }
        //size*(page-1)
        pagunationDto.setpagunation(totalPage,page);
        Integer offset =size *(page-1);
        List<Question> questions=questionMapper.listByUserId(userId,offset,size);
        List<QuestionDto> questionDtoList =new ArrayList<>();
        PagunationDto pagunationDto1 = new PagunationDto();
        for (Question question : questions){
            User user =usermapper.findById(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pagunationDto.setQuestions(questionDtoList);
        return pagunationDto;
    }

    public QuestionDto getById(Integer id) {

       Question question =questionMapper.getById(id);
       if (question==null){
           throw new CustomizeException("你找的问题没啦，哈哈哈哈");
       }

        QuestionDto questionDto=new QuestionDto();
        BeanUtils.copyProperties(question,questionDto);
        User user =usermapper.findById(question.getCreator());
        questionDto.setUser(user);
        return questionDto;
    }

    public void createOrupdate(Question question) {
        if (question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            //创建
            questionMapper.create(question);
        }else{
            //更新
            question.setGmtModified(question.getGmtModified());
            questionMapper.update(question);

        }
    }
}
