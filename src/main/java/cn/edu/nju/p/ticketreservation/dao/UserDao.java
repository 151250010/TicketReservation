package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.dao.entity.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from user_info where email=#{email}")
    UserInfo getUserInfoByEmail(String email);

    @Insert("insert into user_info (userName,email,age,infoState,sex,score,consumption) values (#{info.userName},#{info.email},#{info.age},#{info.infoState},#{info.sex},0,0)")
    void addUser(@Param("info") UserInfo userInfo);

    @Update("update user_info set infoState=1 where email=#{email}")
    void cancelUser(String email);

    @Update("update user_info set score=score+#{money} and consumption=consumption+#{money} where email=#{email}")
    void updateUserScore(@Param("email") String email, @Param("money") double money);
}
