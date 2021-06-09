package main.dao;

import main.model.Ad;

import javax.ejb.Stateless;

@Stateless
public class AdDaoImpl extends AbstractDaoJpaImpl<Ad> implements AdDao {
}
