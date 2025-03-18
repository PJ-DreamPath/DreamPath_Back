package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Board;
import com.korit.dreampath_back.mapper.BoardMapper;
import com.korit.dreampath_back.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {

    @Autowired
    private BoardMapper boardMapper;


//    boardName으로 찾은 boardId
public Board findBoardIdByBoardName(String boardNme) {
        return boardMapper.selectBoardIdByBoardName(boardNme);
    }
}
