package main.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
@NamedQuery(name="Ad.findInactiveAds",query = "select u from Ad u where u.accepted=false ")
@NamedQuery(name="Ad.findActiveAds",query = "select u from Ad u where u.accepted=true")
@Entity
public class Ad extends AbstractModel{
    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    private String title;

    private boolean accepted;

    public boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

}
