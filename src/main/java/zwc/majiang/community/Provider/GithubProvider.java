package zwc.majiang.community.Provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import zwc.majiang.community.dto.AcessToken;
import zwc.majiang.community.dto.Githubuser;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAcesstoke(AcessToken acessToken){
         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();
            RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(acessToken));
            Request request = new Request.Builder()
                    .url("https://github.com/login/oauth/access_token")
                    .post(body)
                    .build();
            try (Response response = client.newCall(request).execute()) {
                String string=response.body().string();
                String token =string.split("&")[0].split("=")[1];
                return token;
            //    System.out.println(string);
            //    return string;

            } catch (Exception e) {
                e.printStackTrace();


            }
            return null;
    }

        public Githubuser getuser(String accesstoken){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accesstoken)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String string=response.body().string();
                Githubuser githubuser = JSON.parseObject(string, Githubuser.class);
                return githubuser;
            } catch (IOException e) {
            }
            return null;
        }
    }

