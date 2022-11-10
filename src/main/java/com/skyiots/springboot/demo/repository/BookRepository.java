package com.skyiots.springboot.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import com.skyiots.springboot.demo.model.Books;

@EnableJpaRepositories
@Repository
public interface BookRepository extends JpaRepository<Books, Long>{

}
