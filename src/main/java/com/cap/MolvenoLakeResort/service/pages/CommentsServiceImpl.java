package com.cap.MolvenoLakeResort.service.pages;

import com.cap.MolvenoLakeResort.model.pages.Comments;
import com.cap.MolvenoLakeResort.payload.response.page.CommentsResponse;
import com.cap.MolvenoLakeResort.repository.pages.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {


    private final CommentsRepository commentsRepository;

    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    @Override
    public List<Comments> getAllComments() {

        return commentsRepository.findAll();

    }
    @Override
    public String save(Comments comments) {

        commentsRepository.save(comments);

        return "success";

    }
    @Override
    public String deleteByCommentId(Long commentId) {

        commentsRepository.deleteById(commentId);

        return "success";
    }

    @Override
    public List<CommentsResponse> getCommentsForHome(String lang) {
        List<Comments> commentsList = commentsRepository.findAll();

        List<CommentsResponse> commentsResponseList = new ArrayList<>();
        for (Comments comments:commentsList){
            CommentsResponse response = new CommentsResponse();
            response.setCommentId(comments.getCommentId());
            response.setAuthor(comments.getAuthor());
            if ("zh".equalsIgnoreCase(lang)) {
                response.setCommentContent(comments.getCommentContentZh());
            } else {
                response.setCommentContent(comments.getCommentContent());
            }
            commentsResponseList.add(response);
        }

        return commentsResponseList;
    }
}
