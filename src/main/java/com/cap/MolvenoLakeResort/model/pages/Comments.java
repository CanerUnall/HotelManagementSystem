package com.cap.MolvenoLakeResort.model.pages;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentContent;
    private String commentContentZh;
    private String author;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommentContentZh() {
        return commentContentZh;
    }

    public void setCommentContentZh(String commentContentZh) {
        this.commentContentZh = commentContentZh;
    }


    public Comments(Long commentId, String commentContent, String commentContentZh, String author) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentContentZh = commentContentZh;
        this.author = author;
    }

    public Comments() {
    }
}
