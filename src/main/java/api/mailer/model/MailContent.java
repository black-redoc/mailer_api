package api.mailer.model;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("MailContent")
public class MailContent implements Serializable {
    private String id;
    private String to;
    private String from;
    private String subject;
    private String body;

    public MailContent() { }

    public MailContent(String to, String from, String subject, String body) {
        this.to = to;
        this.from = from;
        this.subject = subject;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "MailContent{" +
                "id=" + id +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
