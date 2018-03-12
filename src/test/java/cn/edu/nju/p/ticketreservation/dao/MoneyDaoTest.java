package cn.edu.nju.p.ticketreservation.dao;

import cn.edu.nju.p.ticketreservation.TicketReservationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyDaoTest {

    @Autowired
    private MoneyDao moneyDao;

    @Test
    public void testAddMoney() {
        moneyDao.plusMoney("administrator", 100);
    }
}