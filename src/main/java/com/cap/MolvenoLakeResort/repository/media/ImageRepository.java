package com.cap.MolvenoLakeResort.repository.media;

import com.cap.MolvenoLakeResort.model.media.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByImageUrl(String url);
    Optional<Image> findByImageName(String imageName);
   // Image findByImageName(String fileName);
}
