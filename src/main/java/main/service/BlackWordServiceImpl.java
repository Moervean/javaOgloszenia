package main.service;

import main.dao.AdDao;
import main.dao.BlackWordDao;
import main.model.Ad;
import main.model.BlackWord;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BlackWordServiceImpl implements BlackWordService{
    @EJB
    private BlackWordDao blackWordDao;
    @Override
    public BlackWord save(BlackWord t) {
        return blackWordDao.save(t);
    }

    @Override
    public void delete(Long id) {
        blackWordDao.delete(id);
    }

    @Override
    public BlackWord findById(Long id) {
        return blackWordDao.findById(id).orElse(null);
    }

    @Override
    public List<BlackWord> findAll() {
        return blackWordDao.findAll();
    }
}
