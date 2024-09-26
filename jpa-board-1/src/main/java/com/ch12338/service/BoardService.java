package com.ch12338.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ch12338.model.NoticeBoard;
import com.ch12338.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

//    public BoardService(BoardRepository BoardRepository) {
//        this.boardRepository = BoardRepository;
//    }

    public List<NoticeBoard> listArticles() {
        List<NoticeBoard> articleList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        return articleList;
    }

    public void addArticle(NoticeBoard noticeBoard) {
        boardRepository.save(noticeBoard);
    }

    public NoticeBoard viewArticle(int articleNo) {
        Optional<NoticeBoard> optional = boardRepository.findById(articleNo);
        NoticeBoard article = null;
        if(optional.isPresent()) {
            article = optional.get();
        }
        return article;
    }

    public void editArticle(NoticeBoard noticeBoard) {
        Optional<NoticeBoard> optional = boardRepository.findById(noticeBoard.getId());
        NoticeBoard article = null;
        if(optional.isPresent()) {
            article = optional.get();
            article.setTitle(noticeBoard.getTitle());
            article.setContent(noticeBoard.getContent());
            boardRepository.save(article);
        }
    }

    public void removeArticle(int articleNo) {
        boardRepository.deleteById(articleNo);
    }
}
