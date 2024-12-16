package com.cap.MolvenoLakeResort.repository.pages;

import com.cap.MolvenoLakeResort.model.pages.Carousel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface CarouselRepository extends JpaRepository<Carousel, Long> {

}
