package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.SiteDao;
import cn.edu.nju.p.ticketreservation.dao.entity.Site;
import cn.edu.nju.p.ticketreservation.enums.SiteState;
import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SiteReg;
import cn.edu.nju.p.ticketreservation.service.SiteService;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private RedisCacheUtil cacheUtil;

    public static final String CACHE_POSTFIX = "_site_info";

    @Override
    public SiteDisplay addSite(SiteReg siteReg) {
        siteReg.setSiteState(SiteState.WAITING_REG_PASS);
        Site site = new Site(siteReg);
        siteDao.addSite(site);
        return new SiteDisplay(site);
    }

    @Override
    public void updateSite(SiteReg siteReg) {
        siteReg.setSiteState(SiteState.WAITING_UPDATE_PASS);
        Site site = new Site(siteReg);
        siteDao.updateSite(site);
    }

    @Override
    public SiteDisplay getSiteInfo(String siteId) {
        if (cacheUtil.cacheExist(siteId + CACHE_POSTFIX)) {
            return cacheUtil.getCache(siteId + CACHE_POSTFIX, SiteDisplay.class);
        }
        int sideIntId = Integer.valueOf(siteId);
        return new SiteDisplay(siteDao.getSite(sideIntId));
    }

    @Override
    public List<SiteDisplay> getAllSites() {

        List<Site> sites = siteDao.getAllSites();
        assert sites != null && sites.size() != 0 : "No Sites Found!";
        List<SiteDisplay> siteDisplays = sites.stream().map(SiteDisplay::new).collect(Collectors.toList());
        return siteDisplays;
    }

}
