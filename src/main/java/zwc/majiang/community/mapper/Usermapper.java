package zwc.majiang.community.mapper;

import org.apache.ibatis.annotations.*;
import zwc.majiang.community.model.User;


    @Mapper
    public interface Usermapper {
        @Select("select * from user where account_id =#{accountId}")
        User findByAccountId(@Param("accountId") String accountId);

        @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
        void insert(User user);
    @Select("select * from user where token =#{token}")
        User findByToken(@Param("token") String token);
     @Select("select * from user where id =#{id}")
        User findById(@Param("id")Integer id);
@Update("update user set name=#{name},token=#{token},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where id=#{id}")
        void update(User dbuser);
    }

