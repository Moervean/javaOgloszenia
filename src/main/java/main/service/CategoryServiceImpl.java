package main.service;

import main.dao.CategoryDao;
import main.model.Category;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class CategoryServiceImpl implements CategoryService {
    @EJB
    private CategoryDao categoryDao;

    @Override
    public Category save(Category t) {
        return categoryDao.save(t);
    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    @Override
    public Category findById(Long id) {
        return categoryDao.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
