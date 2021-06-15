package main.controller;

import main.model.Ad;
import main.model.BlackWord;
import main.service.AdService;
import main.service.BlackWordService;
import main.service.CategoryService;
import main.service.UserService;
import main.util.JSF;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AdController implements Serializable {

    @EJB
    private UserService userService;

    @EJB
    private BlackWordService blackWordService;

    @EJB
    private AdService adService;

    @EJB
    private CategoryService categoryService;

    private List<Ad> ads ;
    private List<Ad> displayedAds;
    private Ad editedAd;
    private Long id;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    private String filter= "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @PostConstruct
    private void init(){
        ads = adService.findActiveAds();
        if(ads == null)
            ads = new ArrayList<>();
        displayedAds = new ArrayList<>();
        ads.forEach(ad -> {
            displayedAds.add(ad);
        });
    }

    public List<Ad> getDisplayedAds() {
        return displayedAds;
    }

    public void setDisplayedAds(List<Ad> displayedAds) {
        this.displayedAds = displayedAds;
    }

    public AdService getAdService() {
        return adService;
    }

    public void setAdService(AdService adService) {
        this.adService = adService;
    }

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }

    public Ad getEditedAd() {
        return editedAd;
    }

    public void setEditedAd(Ad editedAd) {
        this.editedAd = editedAd;
    }



    public void onAddAd(){
        editedAd = new Ad();
    }

    public void onEditAd(Ad a){
        editedAd = a;
    }

    private boolean hasBlackWords(String text) {
        List<BlackWord> blackWords = blackWordService.findAll();

        if (blackWords.isEmpty() || text == null || text.isEmpty())
            return false;

        for (BlackWord word : blackWordService.findAll()) {
            if (text.contains(word.getWord()))
                return true;
        }
        return false;
    }

    public void onSaveAd(String login) {

        if (hasBlackWords(editedAd.getTitle()) || hasBlackWords(editedAd.getContent())) {
            editedAd = null;
            JSF.addErrorMessage("Ogłoszenie zawiera zabronione słowa");
            return;
        }

        editedAd.setUser(userService.findByLogin(login));
        editedAd.setCategory(categoryService.findById(id));
        if(editedAd.getId() == null){
            ads.add(editedAd);
        }

        Ad saved = adService.save(editedAd);
        ads.replaceAll(a-> a != editedAd ? a : saved);
        init();
        editedAd = null;

        JSF.addInfoMessage("Ogłoszenie czeka na akceptację moderatora.");
    }

    public void onRemoveAd(Ad a){
        adService.delete(a.getId());
        ads.remove(a);
        displayedAds.remove(a);
    }

    public void onCancelAd(){
        ads.replaceAll(a-> a != editedAd ? a : adService.findById(editedAd.getId()));

        editedAd = null;
    }

    public void searchAds(){
        displayedAds = new ArrayList<>();
        ads.forEach(ad -> {
            displayedAds.add(ad);
        });
        List<Ad> tmpAds = new ArrayList<>();
        displayedAds.forEach((ad -> {
            if(!ad.getContent().contains(filter))
                tmpAds.add(ad);
        }));
        tmpAds.forEach(ad -> {
            displayedAds.remove(ad);
        });
    }


}
