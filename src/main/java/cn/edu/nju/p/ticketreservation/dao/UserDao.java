package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.dao.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository
public interface UserDao {

    @Select("select * from user_info where email=#{email}")
    UserInfo getUserInfoByEmail(String email);
}
