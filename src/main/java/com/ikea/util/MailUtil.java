package com.ikea.util;

import com.ikea.entity.base.MailEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class MailUtil {

  public static void sendUsername(JavaMailSender mailSender, MailEntity mailEntity) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
    mimeMessageHelper.setTo(mailEntity.getTo());
    mimeMessageHelper.setSubject("[IKEA] 아이디 찾기 메일입니다.");
    // 첫번째 파라미터 = 내용 두번쨰 파라미터 html 여부
    mimeMessageHelper.setText(String.format("로그인에 사용되는 아이디는 [%s]입니다.", mailEntity.getUsername()), false);
    mailSender.send(mimeMessage);
  }

  public static void sendPassword(JavaMailSender mailSender, MailEntity mailEntity) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
    mimeMessageHelper.setTo(mailEntity.getTo());
    mimeMessageHelper.setSubject("[IKEA] 비밀번호 찾기 메일입니다.");
    // 첫번째 파라미터 = 내용 두번쨰 파라미터 html 여부
    mimeMessageHelper.setText(String.format("재설정된 비밀번호는 [%s]입니다.\n로그인이후 보안상 위험하므로 새로운 비밀번호로 변경해주세요.", mailEntity.getPassword()), false);
    mailSender.send(mimeMessage);
  }

}
