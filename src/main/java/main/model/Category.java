package main.model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQuery(name=Category.FIND_BY_NAME, query = "Select a from User a where a.login =:login and a.password =:password")
public class Category extends AbstractModel{
    public static final String FIND_BY_NAME = "Category.findByName";

    private String name;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Ad> adList;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> asList) {
        this.adList = asList;
    }
}
