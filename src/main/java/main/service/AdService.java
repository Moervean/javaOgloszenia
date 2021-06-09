package main.service;

import main.model.Ad;

import java.util.List;

public interface AdService {
    Ad save(Ad t);
    void delete(Long id);
    Ad findById(Long id);
    List<Ad> findAll();
}
