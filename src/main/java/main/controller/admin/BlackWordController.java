package main.controller.admin;

import main.model.Ad;
import main.model.BlackWord;
import main.service.AdService;
import main.service.BlackWordService;

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
public class BlackWordController implements Serializable {

    private final static Logger log = Logger.getLogger(BlackWordController.class.getName());

    @EJB
    private BlackWordService blackWordService;
    private List<BlackWord> blackWords ;
    private BlackWord editedBlackWord;

    @PostConstruct
    private void init(){
        blackWords = blackWordService.findAll();
        if(blackWords == null)
            blackWords = new ArrayList<>();
    }

    public void setBlackWords(List<BlackWord> blackWords) {
        this.blackWords = blackWords;
    }

    public void setBlackWordService(BlackWordService blackWordService) {
        this.blackWordService = blackWordService;
    }

    public void setEditedBlackWord(BlackWord editedBlackWord) {
        this.editedBlackWord = editedBlackWord;
    }

    public BlackWord getEditedBlackWord() {
        return editedBlackWord;
    }

    public BlackWordService getBlackWordService() {
        return blackWordService;
    }

    public List<BlackWord> getBlackWords() {
        return blackWords;
    }

    public void onAddAd(){
        editedBlackWord = new BlackWord();
    }

    public void onEditAd(BlackWord a){
        editedBlackWord = a;
    }

    public void onSaveAd(){
        if(editedBlackWord.getId() == null){
            blackWords.add(editedBlackWord);
            log.info("Zakazane slowo dodane");
        }

        BlackWord saved = blackWordService.save(editedBlackWord);
        blackWords.replaceAll(a-> a != editedBlackWord ? a : saved);

        editedBlackWord = null;
    }

    public void onRemoveAd(BlackWord a){
        log.info("Zakazane słowo usuniete");
        blackWordService.delete(a.getId());
        blackWords.remove(a);
    }

    public void onCancelAd(){
        log.info("Edycja zakazanego słowa przerwana");
        blackWords.replaceAll(a-> a != editedBlackWord ? a : blackWordService.findById(editedBlackWord.getId()));

        editedBlackWord = null;
    }

}
