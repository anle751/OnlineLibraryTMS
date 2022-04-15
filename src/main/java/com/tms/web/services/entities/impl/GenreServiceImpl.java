package com.tms.web.services.entities.impl;

import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.entities.repositories.GenreRepository;
import com.tms.web.services.entities.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;
    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findByIds(List<Long> genreIds) {
        return genreRepository.findByIds(genreIds);
    }

    @Override
    public Genre save(Genre genre) {
        List<Genre> fromDB = genreRepository.findByName(genre.getName());
        if (fromDB.size() ==0) {
            return genreRepository.save(genre);
        }
        return null;
    }

    @Override
    public List<Genre> findByName(String name) {
        return genreRepository.findByName(name);
    }

}
