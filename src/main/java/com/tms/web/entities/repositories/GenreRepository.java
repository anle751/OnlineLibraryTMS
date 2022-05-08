package com.tms.web.entities.repositories;

import com.tms.web.entities.library.Genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {

    public List<Genre> findByIdIn(List<Long> ids);
    public List<Genre> findByName(String name);
}
