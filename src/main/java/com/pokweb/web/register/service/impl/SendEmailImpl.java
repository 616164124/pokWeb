package com.pokweb.web.register.service.impl;

import com.pokweb.common.response.WebResponse;
import com.pokweb.web.register.service.SendMsgService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class SendEmailImpl implements SendMsgService {
    @Resource
    private JavaMailSender javaMailSender;

    /**
     *
     * @param subject 主题
     * @param text  内容
     * @param to    对方电子邮箱
     * @param from  己方电子邮箱
     * @return
     */
    @Override
    public WebResponse sendEmail(String subject,String text,String to,String from) {
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件设置
        message.setSubject(subject);
        message.setText(text);
        message.setTo(to);
        message.setFrom(from);
        try {
            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
            return WebResponse.error("发送邮件出错", "");
        }
        return WebResponse.ok("简单邮件发送成功");
    }
}
