package main.controller;

        import main.model.Ad;
        import main.service.AdService;
        import main.service.UserService;

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
public class ModAdController implements Serializable {


    private final static Logger log = Logger.getLogger(ModAdController.class.getName());
    @EJB
    private UserService userService;

    @EJB
    private AdService adService;
    private List<Ad> ads ;
    private Ad editedAd;

    @PostConstruct
    private void init(){
        ads = adService.findInactiveAds();
        //ads = adService.findAll();
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

    public void onSaveAd(){
        log.info("Ogłoszenie zapisanie");
        if(editedAd.getId() == null){
            ads.add(editedAd);
        }

        Ad saved = adService.save(editedAd);
        ads.replaceAll(a-> a != editedAd ? a : saved);

        editedAd = null;
    }

    public void onRemoveAd(Ad a){
        log.info("Ogłoszenie usunięte");
        adService.delete(a.getId());
        ads.remove(a);
    }

    public void onCancelAd(){
        log.info("Edycja przerwana");
        ads.replaceAll(a-> a != editedAd ? a : adService.findById(editedAd.getId()));

        editedAd = null;
    }


}
