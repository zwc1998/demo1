package zwc.majiang.community.dto;

import lombok.Data;
import zwc.majiang.community.model.Question;

import java.util.ArrayList;
import java.util.List;
@Data
public class PagunationDto {
    private List<QuestionDto> questions;
    private boolean showprevious;
    private boolean showfirstpage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;

    public void setpagunation(Integer totalPage, Integer page) {

        this.totalPage=totalPage;
        this.page=page;
        pages.add(page);
        for (int i=1;i<=3;i++)
              {
                  if (page -i>0){
                      pages.add(0,page-i);
                  }
     if (page+i<=totalPage){
         pages.add(page+i);
     }
        }
if (page==1){
    showprevious =false;
}else {
    showprevious=true;
}
if (page==totalPage){
    showNext=false;
}else{
    showNext=true;
    }
if (pages.contains(1)){
    showfirstpage=false;
}else{
    showfirstpage=true;
}
if (pages.contains(totalPage)){
    showEndPage=false;
}else{
    showEndPage=true;
}

}
}
