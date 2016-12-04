package com.vanya.auth;

import com.vanya.model.UnconfirmedUser;
import com.vanya.model.User;
import com.vanya.remote.data.access.RemoteDataAccessFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.Properties;

import static java.lang.String.format;

/**
 * Created by Hladush Ivan
 * on 04.12.16.
 */
@Component
public class EmailSender {

    private JavaMailSenderImpl mailSender;

    @Autowired
    private RemoteDataAccessFacade remoteDataAccessFacade;

    @Value("${mail.port}")
    private String port;

    @Value("${mail.host}")
    private String host;

    @Value("${mail.username}")
    private String userName;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.protocol}")
    private String protocol;

    @Value("${email.template.confirmation.email}")
    private String emailTemplate;

    @Value("${host.address}")
    private String hostAddress;

    @PostConstruct
    public void afterPropertySet() {
        mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.valueOf(port));
        mailSender.setUsername(userName);
        mailSender.setPassword(password);
        mailSender.setProtocol(protocol);
        Properties properties = new Properties();
        properties.setProperty("mail.smtps.auth", "true");
        mailSender.setJavaMailProperties(properties);
    }

    public void sendEmailForCorfirmation(User user) {
        try {
            UnconfirmedUser unconfirmedUser = UnconfirmedUser.createUnconfirmedUser(user);
            remoteDataAccessFacade.saveUnconfirmedUser(unconfirmedUser);
            sendEmail(unconfirmedUser);
        } catch (Exception ex) {
            throw new IllegalStateException("Can't send message");
        }
    }

    public void sendEmail(UnconfirmedUser unconfirmedUser) throws MessagingException {
        MimeMessageHelper simpleMailMessage = new MimeMessageHelper(mailSender.createMimeMessage(), true);
        simpleMailMessage.setFrom(userName);
        simpleMailMessage.setSubject("Email for confirmation registration");
        simpleMailMessage.setTo(unconfirmedUser.getEmail());
        simpleMailMessage.setText(createText(unconfirmedUser),
                true);
        mailSender.send(simpleMailMessage.getMimeMessage());
    }

    private String createText(UnconfirmedUser unconfirmedUser) {
        String link = format("http://%s:8080", hostAddress);
        return format(emailTemplate,
                link,
                link + "/confirmationRegistration/" + unconfirmedUser.getId(),
                "Confirm  registration");
    }
}

