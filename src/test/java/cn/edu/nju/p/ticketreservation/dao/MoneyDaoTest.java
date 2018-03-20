package cn.edu.nju.p.ticketreservation.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyDaoTest {

    @Autowired
    private MoneyDao moneyDao;

    @Test
    public void testAddMoney() {
        moneyDao.plusMoney("administrator", 100);
    }

    @Test
    public void getSiteMoney() {
        System.out.println(moneyDao.getSiteMoney(30));
    }

    @Test
    public void settle() {
        moneyDao.settle(0.8);
    }
}