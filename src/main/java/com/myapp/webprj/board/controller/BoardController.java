package com.myapp.webprj.board.controller;

import com.myapp.webprj.board.domain.Board;
import com.myapp.webprj.board.service.BoardService;
import com.myapp.webprj.common.Criteria;
import com.myapp.webprj.common.PageMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board/*")
@Log4j2 //로그에 시간이 나오게 해줌
public class BoardController {

    private final BoardService boardService;

    //게시물 목록 요청 처리
    @GetMapping("/list")
    public String list(Criteria cri, Model model) {
        log.info("/board/list Get 요청 발생!: " + cri);
        List<Board> list = boardService.getList(cri);
        model.addAttribute("list", list);
        model.addAttribute("pageInfo", new PageMaker(cri,boardService.getTotal()));
        return "board/list";
    }

    //게시글 등록 화면 요청 처리
    @GetMapping("/register")
    public String register() {
        log.info("/board/register GET 요청!");
        return "board/register";
    }

    //게시글 등록 처리 요청
    @PostMapping("/register")
    public String register(Board board, RedirectAttributes ra) {
        log.info("/board/register POST 요청: " + board);
        boardService.register(board);
        ra.addFlashAttribute("msg", "regSuccess");
        return "redirect:/board/list";
    }

    //게시글 상세 조회 요청
    @GetMapping("/get")
    public String get(Long bno, Model model) {
        log.info("/board/get GET 요청! : " + bno);
        Board board = boardService.get(bno);
        model.addAttribute("board", board);
        return "board/get";
    }

    //게시글 수정 화면 요청
    @GetMapping("/modify")
    public String modify(Long bno, Model model) {
        log.info("/board/modify MODIFY 요청!");
        Board board = boardService.get(bno);
        model.addAttribute("board", board);
        return "board/modify";
    }

    //게시글 수정 완료 처리 요청
    @PostMapping("/modify")
    public String modify(Board board, RedirectAttributes ra) {
        log.info("/board/modify MODIFY 요청 : " + board);
        boolean modify = boardService.modify(board);
        if (modify) {
            ra.addFlashAttribute("msg", "modifySuccess");
        }
        return "redirect:/board/list";
    }

    //게시글 삭제 완료처리 요청
    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes ra) {
        log.info("/board/remove POST! " + bno);
        boolean delete = boardService.remove(bno);
        if (delete) {
            ra.addFlashAttribute("msg", "delSuccess");
        }
        return "redirect:/board/list";
    }


}
