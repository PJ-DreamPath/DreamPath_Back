package com.korit.dreampath_back.service;

import com.korit.dreampath_back.dto.request.ReqApplyEmailDto;
import com.korit.dreampath_back.entity.MentoringRegister;
import com.korit.dreampath_back.repository.ApplyRepository;
import com.korit.dreampath_back.security.jwt.JwtUtil;
import com.korit.dreampath_back.security.principal.PrincipalUser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class ApplyService {
    @Autowired
    private JwtUtil jwtUtil;

    private final String FROM_EMAIL = "rlatldnr1234@gmail.com";

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired
    private ApplyRepository applyRepository;

    public String sendApplyMail(ReqApplyEmailDto reqApplyEmailDto, PrincipalUser principalUser) throws MessagingException {
        String nickname = principalUser.getUser().getNickname();
        String emailToken = jwtUtil.generateToken(null, null, new Date(new Date().getTime()*1000l*60*60*24*7));
        String email = reqApplyEmailDto.getEmail();
        String message = "";

        final String SUBJECT = "멘토링 신청 이메일입니다.";

        String content = String.format("""
                <html lang="ko">
                    <head>
                        <meta charset="UTF-8">
                    </head>
                    <body>
                        <div style="display: flex; flex-direction: column; align-items: center; ">
                            <h1>멘토링 신청 승인 확인</h1>
                            <p>%s님께서 귀하의 멘토링 수업에 참가하고자 합니다.</p>
                            <p>멘토링 신청자 이메일 주소 : %s</p>
                            <p>멘토링을 진행하시려면 위의 주소로 연락 바랍니다.</p>
                        </div>
                    </body>
                    </html>
                """, nickname, email);
        if(isApplied(principalUser, reqApplyEmailDto)){
            sendMail(email, SUBJECT, content);
            applyRepository.insertMentoringRegister(MentoringRegister.builder().userId(principalUser.getUser().getUserId()).postId(reqApplyEmailDto.getPostId()).build());
            message = "신청 메일 전송에 성공했습니다.";
        } else {
            message = "이미 신청한 멘토링입니다.";
        }
        return message;
    }


    public void sendMail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
        mimeMessageHelper.setFrom(FROM_EMAIL);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(subject);
        mimeMessage.setText(content, StandardCharsets.UTF_8.name(), "html");
        mailSender.send(mimeMessage);
    }

    public boolean isApplied (PrincipalUser principalUser, ReqApplyEmailDto reqApplyEmailDto) {
        System.out.println(applyRepository.getMentoringRegisterList(principalUser.getUser().getUserId(), reqApplyEmailDto.getPostId()).get().isEmpty());
        return applyRepository.getMentoringRegisterList(principalUser.getUser().getUserId(), reqApplyEmailDto.getPostId()).get().isEmpty();
    }
}
