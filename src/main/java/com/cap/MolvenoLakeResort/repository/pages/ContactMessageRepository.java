package com.cap.MolvenoLakeResort.repository.pages;

import com.cap.MolvenoLakeResort.model.pages.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
