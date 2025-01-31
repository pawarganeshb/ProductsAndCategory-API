package com.nimap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nimap.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	//server side pagination
	Page<Category> findAll(Pageable pageable);
}
