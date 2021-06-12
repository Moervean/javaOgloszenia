package main.dao;

import main.model.Ad;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class AdDaoImpl extends AbstractDaoJpaImpl<Ad> implements AdDao {
    @Override
    public List<Ad> findActiveAds() {
        {
            return findList("Ad.findActiveAds");
        }
    }

    @Override
    public List<Ad> findInactiveAds() {
        return findList("Ad.findInactiveAds");
    }
}
