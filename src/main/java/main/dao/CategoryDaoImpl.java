package main.dao;


import main.model.Category;

import javax.ejb.Stateless;

@Stateless
public class CategoryDaoImpl extends AbstractDaoJpaImpl<Category> implements CategoryDao {
}
