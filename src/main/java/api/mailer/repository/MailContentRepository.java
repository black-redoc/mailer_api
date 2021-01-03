package api.mailer.repository;

import api.mailer.model.MailContent;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MailContentRepository extends CrudRepository<MailContent, String> {
    List<MailContent> findByTo(String to);
    List<MailContent> findAll();
}
