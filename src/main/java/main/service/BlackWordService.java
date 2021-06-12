package main.service;

import main.model.Ad;
import main.model.BlackWord;

import java.util.List;

public interface BlackWordService {
    BlackWord save(BlackWord t);
    void delete(Long id);
    BlackWord findById(Long id);
    List<BlackWord> findAll();
}
