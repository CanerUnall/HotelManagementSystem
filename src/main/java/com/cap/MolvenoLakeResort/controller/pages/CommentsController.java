package com.cap.MolvenoLakeResort.controller.pages;

import com.cap.MolvenoLakeResort.model.pages.Comments;
import com.cap.MolvenoLakeResort.payload.response.page.CommentsResponse;
import com.cap.MolvenoLakeResort.service.pages.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }


    @GetMapping
    public ResponseEntity<List<Comments>> getAllComments() {

        return ResponseEntity.ok(commentsService.getAllComments());

    }

    @PostMapping
    public ResponseEntity<String> saveComment(@RequestBody Comments comments) {

        return ResponseEntity.ok(commentsService.save(comments));

    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteByCommentId(@PathVariable("commentId") Long commentId) {

        return ResponseEntity.ok(commentsService.deleteByCommentId(commentId));
    }

    @GetMapping("/home")
    public ResponseEntity<List<CommentsResponse>> getCommentsForHome(@RequestParam String lang) {
        System.out.println("lang = " + lang);

        return ResponseEntity.ok(commentsService.getCommentsForHome(lang));
    }

}
