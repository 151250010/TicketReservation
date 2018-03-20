package cn.edu.nju.p.ticketreservation.service.impl;

import cn.edu.nju.p.ticketreservation.dao.MoneyDao;
import cn.edu.nju.p.ticketreservation.dao.SiteDao;
import cn.edu.nju.p.ticketreservation.dao.entity.Site;
import cn.edu.nju.p.ticketreservation.enums.SiteState;
import cn.edu.nju.p.ticketreservation.interact.display.SiteDisplay;
import cn.edu.nju.p.ticketreservation.interact.input.SiteReg;
import cn.edu.nju.p.ticketreservation.service.SiteService;
import cn.edu.nju.p.ticketreservation.utils.RedisCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteDao siteDao;

    @Autowired
    private MoneyDao moneyDao;

    @Autowired
    private RedisCacheUtil cacheUtil;

    public static final String CACHE_POSTFIX = "_site_info";

    @Override
    @Transactional
    public SiteDisplay addSite(SiteReg siteReg) {
        siteReg.setSiteState(SiteState.WAITING_REG_PASS);
        Site site = new Site(siteReg);
        siteDao.addSite(site);
        moneyDao.addMoneyAccount(new DecimalFormat("0000000").format(site.getId()));
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

        String key = siteId + CACHE_POSTFIX;
        if (cacheUtil.cacheExist(key)) {
            return cacheUtil.getCache(key, SiteDisplay.class);
        }
        int sideIntId = Integer.valueOf(siteId);
        SiteDisplay result = new SiteDisplay(siteDao.getSite(sideIntId));
        cacheUtil.putCacheWithExpireTime(key, result, 60 * 30);
        return result;
    }

    @Override
    public List<SiteDisplay> getAllSites() {
        List<Site> sites = siteDao.getAllSites();
        assert sites != null && sites.size() != 0 : "No Sites Found!";
        return sites.stream().map(SiteDisplay::new).collect(Collectors.toList());
    }

    @Override
    public List<SiteDisplay> getAllUncheckSites() {
        List<Site> sites = siteDao.getAllUncheckSites();
        return sites.stream().map(SiteDisplay::new).collect(Collectors.toList());
    }

    @Override
    public void updateSiteStatus(String siteId, int status) {
        siteDao.updateSiteStatus(Integer.valueOf(siteId), status);
    }

}
