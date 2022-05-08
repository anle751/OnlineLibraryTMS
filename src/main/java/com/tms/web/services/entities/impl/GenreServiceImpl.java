package com.tms.web.services.entities.impl;

import com.tms.web.entities.library.Genre.Genre;
import com.tms.web.entities.library.Genre.GenreComparator;
import com.tms.web.entities.repositories.GenreRepository;
import com.tms.web.services.entities.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Override
    public Set<Genre> getAll() {
        Set<Genre> genres = new TreeSet<Genre>(new GenreComparator());
        genres.addAll(genreRepository.findAll());
        return genres;
    }

    @Override
    public List<Genre> findByIds(List<Long> genreIds) {
        return genreRepository.findByIdIn(genreIds);
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
