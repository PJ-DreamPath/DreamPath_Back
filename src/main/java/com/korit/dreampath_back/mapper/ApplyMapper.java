package com.korit.dreampath_back.mapper;

import com.korit.dreampath_back.entity.MentoringRegister;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyMapper {

    List<MentoringRegister> getMentoringRegisterList(int userId, int postId);

    int insertMentoringRegister(MentoringRegister mentoringRegister);
}
