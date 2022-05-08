package com.tms.web.services.busines;

import java.util.List;

public interface SearchService<T> {
    public List<T> search(String searchText);
}
