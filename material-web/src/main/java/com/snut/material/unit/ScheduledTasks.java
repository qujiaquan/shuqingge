package com.snut.material.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

/**
 * 启动定时执行，用来进行定时任务的发送
 */
@Component
public class ScheduledTasks {


    @Autowired
    private JavaMailSender javaMailSender;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void secondTask() {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = null;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            // 邮件发送来源
            mimeMessageHelper.setFrom("抒情阁<3287033742@qq.com>");
            // 邮件发送目标
            mimeMessageHelper.setTo("3451502830@qq.com");
            // 设置标题
            mimeMessageHelper.setSubject("主题：邮箱验证码");

            for (int i = 0; i < 4; i++) {
                /*if (news.get(i).getId() == 12){
                    // 设置内容，并设置内容 html 格式为 true
                    mimeMessageHelper.setText(news.get(i).getContent(), true);
                    javaMailSender.send(mimeMessage);
                }*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



/*
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("抒情阁<3287033742@qq.com>");
        message.setTo("3451502830@qq.com");
        message.setSubject("主题：邮箱验证码");

        for (int i = 0; i < 5; i++) {
            if (news.get(i) != null){
                message.setText(news.get(i).getContent());
                javaMailSender.send(message);//发送邮箱
            }
        }
*/

    }
}
