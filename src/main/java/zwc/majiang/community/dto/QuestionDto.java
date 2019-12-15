package zwc.majiang.community.dto;

import lombok.Data;
import zwc.majiang.community.model.User;

@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer Creator;
    private Integer viewcount;
    private Integer commentcount;
    private Integer likecount;
    private User user;


}
