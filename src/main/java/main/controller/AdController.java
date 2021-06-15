package main.controller;

import main.model.Ad;
import main.model.BlackWord;
import main.service.AdService;
import main.service.BlackWordService;
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
    private List<Ad> ads ;
    private Ad editedAd;

    @PostConstruct
    private void init(){
        ads = adService.findActiveAds();
        if(ads == null)
            ads = new ArrayList<>();
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

        if(editedAd.getId() == null){
            ads.add(editedAd);
        }

        Ad saved = adService.save(editedAd);
        ads.replaceAll(a-> a != editedAd ? a : saved);
        init();
        editedAd = null;
    }

    public void onRemoveAd(Ad a){
        adService.delete(a.getId());
        ads.remove(a);
    }

    public void onCancelAd(){
        ads.replaceAll(a-> a != editedAd ? a : adService.findById(editedAd.getId()));

        editedAd = null;
    }


}
