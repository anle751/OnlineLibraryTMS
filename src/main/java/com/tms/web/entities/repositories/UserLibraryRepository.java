package com.tms.web.entities.repositories;

import com.tms.web.entities.library.UserLibrary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLibraryRepository extends JpaRepository<UserLibrary, Long> {
}