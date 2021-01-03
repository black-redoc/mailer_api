package api.mailer.controller;

import api.mailer.model.MailContent;
import api.mailer.repository.MailContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MailerController {
    @Autowired JavaMailSender sender;
    @Autowired MailContentRepository mailRepository;

    @PostMapping(value = "/api/mail")
    @SuppressWarnings({"rawtypes"})
    public ResponseEntity sendEmail(@RequestBody MailContent mail) {
        return sendEmailUtil(mail)
                ? new ResponseEntity<>(HttpStatus.ACCEPTED)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private boolean sendEmailUtil(MailContent content) {
        boolean sent = false;
        mailRepository.save(content);
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            message.setFrom(content.getFrom());
            helper.setTo(content.getTo());
            helper.setText(content.getBody());
            helper.setSubject(content.getSubject());
            sender.send(message);
            sent = true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return sent;
    }
    @GetMapping(value = "/api/mail/{email}")
    public ResponseEntity<List<MailContent>> getMails(@PathVariable String email) {
        final List<MailContent> mails = mailRepository
                .findAll()
                .stream()
                .filter(e -> e.getTo().equalsIgnoreCase(email) || e.getFrom().equalsIgnoreCase(email))
                .collect(Collectors.toList());
        return new ResponseEntity<>(mails, HttpStatus.FOUND);
    }

    @GetMapping(value = "/api/mail")
    public ResponseEntity<List<MailContent>> getMails() {
        return new ResponseEntity<>(mailRepository.findAll(), HttpStatus.FOUND);
    }
}
