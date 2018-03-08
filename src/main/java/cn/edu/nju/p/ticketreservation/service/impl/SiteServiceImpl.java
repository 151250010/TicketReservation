package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.SiteDao;
import cn.edu.nju.p.ticketreservation.dao.entity.Site;
import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SiteReg;
import cn.edu.nju.p.ticketreservation.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteDao siteDao;

    @Override
    public SiteDisplay addSite(SiteReg siteReg) {
        Site site = new Site(siteReg);
        siteDao.addSite(site);
        return new SiteDisplay(site);
    }

    @Override
    public void updateSite(SiteReg siteReg) {
        Site site = new Site(siteReg);
        siteDao.updateSite(site);
    }
}
