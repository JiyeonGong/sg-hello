package com.ch12338.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ch12338.model.NoticeBoard;
import com.ch12338.service.BoardService;
import com.ch12338.service.UserService;

import lombok.RequiredArgsConstructor;


@Controller("boardController")
@RequestMapping("/notice")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;
    //private NoticeBoard noticeBoard;
    private List<NoticeBoard> articleList;

    Logger logger = LoggerFactory.getLogger("com.hello.controller.BoardController");

    @RequestMapping({"/list", "/"})
    public String getAricleList(Model model) {
        articleList = boardService.listArticles();
        model.addAttribute("dataList", articleList);
        logger.info("전제 조회");
        return "list";
    }

    @RequestMapping("/add")
    @PreAuthorize("isAuthenticated")
    public String writeArticle() {
        return "write";
    }

    @PostMapping(value = "/addarticle")
    @PreAuthorize("isAuthenticated")
    public String addArticle(@RequestParam(value = "i_title") String title
            , @RequestParam(value = "i_content") String content
            , Principal principal) {
        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.setTitle(title);
        noticeBoard.setContent(content);
        noticeBoard.setWriter(userService.getUser(principal.getName()));
        // 로그인 시 사용자ID를 writeId로
        boardService.addArticle(noticeBoard);
        logger.info("게시글 추가: " + title);

        return "redirect:list";
    }

    @GetMapping("/view")
    public ModelAndView viewArticle(@RequestParam(value = "no") String articleNo) {
        NoticeBoard noticeBoard = boardService.viewArticle(Integer.parseInt(articleNo));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view");
        mv.addObject("article", noticeBoard);
        logger.info("상세 조회:" + articleNo);
        return mv;
    }

    @PostMapping("/edit")
    @PreAuthorize("isAuthenticated")
    public String editArticle(@RequestParam String articleNo
            , @RequestParam String title
            , @RequestParam String content
            , RedirectAttributes redirectAtt) {
        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.setId(Integer.parseInt(articleNo));
        noticeBoard.setTitle(title);
        noticeBoard.setContent(content);
        boardService.editArticle(noticeBoard);
        redirectAtt.addAttribute("no", articleNo);
        return "redirect:view";
    }

    @PostMapping("/remove")
    @PreAuthorize("isAuthenticated")
    public String removeArticle(@RequestParam String articleNo) {
        boardService.removeArticle(Integer.parseInt(articleNo));
        logger.info("게시글 삭제: " + articleNo);
        return "redirect:list";
    }

}
