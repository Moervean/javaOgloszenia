package main.dao;

import main.model.Ad;
import main.model.User;

import java.util.List;
import java.util.Optional;

public interface AdDao extends AbstractDao<Ad> {
    List<Ad> findActiveAds();
    List<Ad> findInactiveAds();
}
