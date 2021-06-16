package main.controller.admin;

import main.model.Category;
import main.service.CategoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class CategoriesController implements Serializable {

    private final static Logger log = Logger.getLogger(CategoriesController.class.getName());

    @EJB
    private CategoryService categoryService;
    private List<Category> categories ;
    private Category editedCategory;

    @PostConstruct
    private void init(){

        categories = categoryService.findAll();
        if(categories == null)
            categories = new ArrayList<>();
    }


    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public void setEditedCategory(Category editedCategory) {
        this.editedCategory = editedCategory;
    }

    public Category getEditedCategory() {
        return editedCategory;
    }

    public CategoryService getCategoryService() {
        return categoryService;
    }

    public List<Category> getCategories() {
        return categories;
    }


    public void onAddCategory(){
        editedCategory = new Category();
    }

    public void onEditCategory(Category category){
        editedCategory = category;
    }

    public void onSaveCategory(){
        if(editedCategory.getId() == null){
            categories.add(editedCategory);
            log.info("Kategoria dodane");
        }

        Category saved = categoryService.save(editedCategory);
        categories.replaceAll(a-> a != editedCategory ? a : saved);

        editedCategory = null;
    }

    public void onRemoveCategory(Category category){
        log.info("Kategoria usunięta");
        categoryService.delete(category.getId());
        categories.remove(category);
    }

    public void onCancelCategory(){
        log.info("Edycja kategorii przerwana");
        categories.replaceAll(a-> a != editedCategory ? a : categoryService.findById(editedCategory.getId()));

        editedCategory = null;
    }
}
