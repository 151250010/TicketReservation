package cn.edu.nju.p.ticketreservation.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlanDaoTest {

    @Autowired
    private PlanDao planDao;

    @Test
    public void getAllCurrentPlans() {

        System.out.println(planDao.getAllCurrentPlans(18));
    }
}