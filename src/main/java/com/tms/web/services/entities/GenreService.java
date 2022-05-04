package com.tms.web.services.entities;

import com.tms.web.entities.library.Genre.Genre;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface GenreService {
//    public List<Genre> getAll();
    public Set<Genre> getAll();
    public List<Genre> findByIds(List<Long> genreIds);
    public Genre save(Genre genre);
    public List<Genre> findByName(String name);
}
