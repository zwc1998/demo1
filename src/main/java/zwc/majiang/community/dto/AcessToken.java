package zwc.majiang.community.dto;

import lombok.Data;

@Data
public class AcessToken {
    private String client_id;
    private String redirect_uri;
    private String code;
    private String client_secret;
    private String state;


}

