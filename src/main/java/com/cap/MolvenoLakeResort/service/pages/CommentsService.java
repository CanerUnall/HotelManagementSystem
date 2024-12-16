package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.Comments;
import com.cap.MolvenoLakeResort.payload.response.page.CommentsResponse;

import java.util.List;

public interface CommentsService {
    List<Comments> getAllComments();

    String save(Comments comments);

    String deleteByCommentId(Long commentId);

    List<CommentsResponse> getCommentsForHome(String lang);
}
