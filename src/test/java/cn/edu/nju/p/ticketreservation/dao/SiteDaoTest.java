package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.TicketReservationApplication;
import cn.edu.nju.p.ticketreservation.dao.entity.Site;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TicketReservationApplication.class)
public class SiteDaoTest {

    @Autowired
    private SiteDao dao;

    @Test
    public void addSite() {
        Site site = new Site(0,"asd","asd","asd","asd",10,10,10);
        site.setProvince("asd");
        site.setAvenue("asd");
        site.setCity("asd");
        site.setName("Test");
        site.setSeatCountX(10);
        site.setSeatCountY(10);
        site.setSeatCountZ(10);
        dao.addSite(site);
        System.out.println("---------------------id------------------" + site.getId());
    }

    @Test
    public void updateSite() {
        Site site = new Site(0,"asd","asd","asd","asd",10,10,10);
        site.setProvince("asd");
        site.setAvenue("asd");
        site.setCity("asd");
        site.setName("Xihao");
        site.setSeatCountX(10);
        site.setSeatCountY(10);
        site.setSeatCountZ(10);
        dao.addSite(site);
        site.setId(1);
        dao.updateSite(site);
    }
}