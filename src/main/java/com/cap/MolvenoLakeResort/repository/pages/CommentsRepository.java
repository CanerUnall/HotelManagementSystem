package com.cap.MolvenoLakeResort.repository.pages;

import com.cap.MolvenoLakeResort.model.pages.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
