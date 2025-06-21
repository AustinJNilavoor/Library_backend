package com.example.Library.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.model.Author;
public interface AuthorRepository extends  JpaRepository<Author, Long>{
    
}
