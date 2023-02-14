package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void save(Board board, User user){
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }
    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board  viewDetail(int id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
            return new IllegalArgumentException("글 상세보기 실패 :   아이디를 찾을 수 없습니다.");
        });
    }
    @Transactional(readOnly = true)
    public void delete(int id) {
        boardRepository.deleteById(id);

    }
    @Transactional
    public void update(int id, Board reqeustBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 찾기 실패 :   아이디를 찾을 수 없습니다.");
                });
        board.setTitle(reqeustBoard.getTitle());
        board.setContent(reqeustBoard.getContent());
        //해당 함수로 종료시(service가 종료될때) 트랜잭션이 종료 됩니다. 이때 더티체킹 - 자동 ㄷ업데이트가 됨 . db flush
    }

}
