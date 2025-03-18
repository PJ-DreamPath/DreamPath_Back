package com.korit.dreampath_back.mapper;


import com.korit.dreampath_back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    int insert(User user);

    User selectById(int userId);
    User selectByUsername(String username);
    User selectByPassword(String password);
    User selectByNickname(String nickname);
    User selectByEmail(String email);
    User selectByAccountNumber(String accountNumber);
    User selectByPhoneNumber(String phoneNumber);
    User selectByAddress(String address);
    User selectByProfileImg(String profileImg);
    User selectByTicketId(String ticketId);
    User selectByStarPoint(String starPoint);
    User selectByRoleList(String roleList);

        int updateProfileImgById(
                @Param("userId") int userId,
                @Param("profileImg") String profileImg);

        int updateNicknameById(
                @Param("userId") int userId,
                @Param("nickname") String nickname);

        int updatePasswordById(
                @Param("userId") int userId,
                @Param("password") String password);

        int updateEmailById(
                @Param("userId") int userId,
                @Param("email") String email
    );
}
