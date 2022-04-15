package com.tms.web.entities.library.Genre;

import java.util.Comparator;

public class GenreComparator implements Comparator<Genre> {

    @Override
    public int compare(Genre o1, Genre o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
