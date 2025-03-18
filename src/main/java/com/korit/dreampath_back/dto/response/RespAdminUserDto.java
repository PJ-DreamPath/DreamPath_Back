package com.korit.dreampath_back.dto.response;

import com.korit.dreampath_back.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RespAdminUserDto {
    private int userId;
    private String username;
    private String email;
    private String nickname;
    private String roleType;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private int remainPoint;

    public RespAdminUserDto(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.phoneNumber = user.getPhoneNumber();
        this.createdAt = user.getCreatedAt();
        this.remainPoint = user.getRemainPoint();

        this.roleType = (user.getUserRoles() != null && !user.getUserRoles().isEmpty()) ?
                user.getUserRoles().stream()
                        .map(userRole -> userRole.getRole() != null ? userRole.getRole().getRoleName() : "UNKNOWN_ROLE")
                        .collect(Collectors.joining(", "))
                : "Role이 없음";
    }
}
