package main.service;

import main.dao.AdDao;
import main.model.Ad;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class AdServiceImpl implements  AdService{
    @EJB
    private AdDao adDao;
    @Override
    public Ad save(Ad t) {
        return adDao.save(t);
    }

    @Override
    public void delete(Long id) {
        adDao.delete(id);
    }

    @Override
    public Ad findById(Long id) {
        return adDao.findById(id).orElse(null);
    }

    @Override
    public List<Ad> findAll() {
        return adDao.findAll();
    }

    @Override
    public List<Ad> findActiveAds() {
        return adDao.findActiveAds();
    }

    @Override
    public List<Ad> findInactiveAds() {
        return adDao.findInactiveAds();
    }
}
