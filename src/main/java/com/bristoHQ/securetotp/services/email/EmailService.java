package com.bristoHQ.securetotp.services.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        String htmlContent =  "<!DOCTYPE html>" +
        "<html lang='en'>" +
        "<head>" +
        "<meta charset='UTF-8'>" +
        "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
        "<title>OTP Verification</title>" +
        "<style>" +
        "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }" +
        ".container { max-width: 450px; margin: 50px auto; background: #ffffff; padding: 25px; " +
        "border-radius: 12px; box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); text-align: center; " +
        "border: 1px solid #ddd; }" +
        ".logo { width: 100px; margin-bottom: 10px; }" +
        ".otp-box { font-size: 26px; font-weight: bold; color: #007bff; padding: 12px; " +
        "border: 2px dashed #007bff; display: inline-block; margin: 15px 0; letter-spacing: 6px; " +
        "border-radius: 5px; background: #f8f9fa; }" +
        "p { color: #333; font-size: 16px; line-height: 1.5; }" +
        ".footer { font-size: 14px; color: #777; margin-top: 20px; }" +
        ".button { display: inline-block; background: #007bff; color: #ffffff; text-decoration: none; " +
        "padding: 10px 20px; border-radius: 5px; font-size: 16px; font-weight: bold; margin-top: 15px; }" +
        "</style>" +
        "</head>" +
        "<body>" +
        "<div class='container'>" +
        "<img class='logo' src='https://imghost.net/ib/0ekrsT3EBByCiyd_1741773835.jpg' alt='Company Logo'>" +
        "<h2>OTP Verification</h2>" +
        "<p>Your One-Time Password (OTP) for verification is:</p>" +
        "<div class='otp-box'>" + otp + "</div>" +
        "<p>This OTP is valid for only 10 minutes. Please do not share it with anyone.</p>" +
        "<p class='footer'>If you did not request this OTP, please ignore this email.</p>" +
        "</div>" +
        "</body>" +
        "</html>";
        try {
            helper.setTo(to);
            helper.setSubject("Your OTP Code");
            helper.setText(htmlContent, true);
        } catch (Exception e) {
            System.out.println("emailservice");
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
