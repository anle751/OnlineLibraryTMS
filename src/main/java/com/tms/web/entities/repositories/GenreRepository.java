package com.tms.web.entities.repositories;

import com.tms.web.entities.library.Genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {


    @Query("select o from Genre o where o.id in :ids" )
    public List<Genre> findByIds(@Param("ids") List<Long> genreIds);
    public List<Genre> findByName(String name);
}
