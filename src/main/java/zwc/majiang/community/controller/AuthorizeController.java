package zwc.majiang.community.controller;

import org.springframework.aop.scope.ScopedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zwc.majiang.community.Provider.GithubProvider;
import zwc.majiang.community.dto.AcessToken;
import zwc.majiang.community.dto.Githubuser;
import zwc.majiang.community.mapper.Usermapper;
import zwc.majiang.community.model.User;
import zwc.majiang.community.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller

public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${Redirect_uri}")
    private String redirectUri;
@Autowired
private UserService userService;
    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request, HttpServletResponse
                           response){
        AcessToken acessToken = new AcessToken();
        acessToken.setClient_id(clientId);
        acessToken.setClient_secret(clientSecret);
        acessToken.setCode(code);
        acessToken.setRedirect_uri(redirectUri);
        acessToken.setState(state);
        //  githubProvider.getAcesstoke(acessToken);
        String acesstoke = githubProvider.getAcesstoke(acessToken);
        Githubuser githubuser = githubProvider.getuser(acesstoke);
        if (githubuser != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubuser.getName());
            user.setAccountId(String.valueOf(githubuser.getId()));
            user.setAvatarUrl(githubuser.getAvatar_url());
            userService.createOrupdate(user);
            response.addCookie(new Cookie("token",token));
            request.getSession().setAttribute("user", githubuser);
            return "redirect:/";
            //登陆成功，写cookie 和session
        } else {
            return "redirect:/";
            //登陆失败，重新登陆
        }

    }
    @GetMapping("/logout")
    public  String logout(HttpServletRequest request,
                          HttpServletResponse response){
        request.getSession().removeAttribute("user");
       Cookie cookie =new Cookie("token",null);
       cookie.setMaxAge(0);
       response.addCookie(cookie);
        return "redirect:/";

    }
}
