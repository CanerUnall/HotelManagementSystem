package com.cap.MolvenoLakeResort.repository.pages;

import com.cap.MolvenoLakeResort.model.pages.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {
}
