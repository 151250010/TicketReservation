package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.dao.entity.Site;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SiteDao {

    @Insert({"insert into site(province,city,avenue,name,seat_count_x,seat_count_y,seat_count_z,check_pass) values " +
            "(#{site.province},#{site.city},#{site.avenue},#{site.name},#{site.seatCountX},#{site.seatCountY},#{site.seatCountZ},#{site.checkPass})"})
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "site.id")
    void addSite(@Param("site") Site site);

    @Update("update site set province=#{site.province},city=#{site.city},avenue=#{site.avenue},name=#{site.name}," +
            "seat_count_x=#{site.seatCountX},seat_count_y=#{site.seatCountY},seat_count_z=#{site.seatCountZ},check_pass=#{site.checkPass} where id=#{site.id}")
    void updateSite(@Param("site") Site site);

    @Select("select * from site where id=#{siteId}")
    Site getSite(int siteId);

    @Select("select * from site")
    List<Site> getAllSites();

}
