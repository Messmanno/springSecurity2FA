package com.mesmer.book_network.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    //something to be added

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailtemplateName emailtemplate,
            String confirmationUrl,
            String activateCode,
            String subject
    ) throws MessagingException {
        String templateName;
        if(emailtemplate == null){
            templateName = "confirm-email";
        }else {
            templateName = emailtemplate.name();
        }

        MimeMessage mimemessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(
                mimemessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED,
                StandardCharsets.UTF_8.name()
        );

        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("confirmationUrl", confirmationUrl);
        properties.put("activateCode", activateCode);

        Context context = new Context();
        context.setVariables(properties);

        messageHelper.setFrom("cool@gmail.com");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);

        String template = templateEngine.process(templateName, context);

        messageHelper.setText(template, true);

        mailSender.send(mimemessage);

    }
}
