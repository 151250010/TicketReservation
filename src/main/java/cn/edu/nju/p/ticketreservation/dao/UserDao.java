package cn.edu.nju.p.ticketreservation.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {

    @Select("select email from user_info where name = #{userName}")
    String getEmailByUsername(String userName);
}
