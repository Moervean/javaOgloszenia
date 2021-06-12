package main.model;

import javax.persistence.Entity;

@Entity
public class BlackWord extends AbstractModel{
    private String word;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
