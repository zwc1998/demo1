package zwc.majiang.community.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zwc.majiang.community.mapper.Usermapper;
import zwc.majiang.community.model.User;

@Service
public class UserService {
    @Autowired
    private Usermapper usermapper;

    public void createOrupdate(User user) {
        User dbuser=usermapper.findByAccountId(user.getAccountId());
        if (dbuser==null){
            //插入
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            usermapper.insert(user);
        }else{
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
dbuser.setName(user.getName());
dbuser.setToken(user.getToken());
            usermapper.update(dbuser);
            //更新
        }

         
    }
}
