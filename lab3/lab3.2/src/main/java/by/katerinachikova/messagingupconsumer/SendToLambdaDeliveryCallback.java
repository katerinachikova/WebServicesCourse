package by.katerinachikova.messagingupconsumer;

import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SendToLambdaDeliveryCallback implements DeliverCallback {
    private final static String lambda_url = "https://a8ewmlezqf.execute-api.us-east-1.amazonaws.com/dev/edit";
    private final static String email_from = "katerina.chikova@gmail.com";
    private final static String email_to = "katerina.chikova@gmail.com";
    private final static String email_subj = "RabbitMQ message";
    private final static String smtp = "email-smtp.us-west-2.amazonaws.com";
    private final static String smtp_user = "AKIAZNYRQLQJIJDYIFXP";
    private final static String smtp_password = "BJeetomynE67gidg/7tzhJf3O8h8VXeAdpimNtcZFBK2";

    private Logger logger = LogManager.getLogger(SendToLambdaDeliveryCallback.class);

    @Override
    public void handle(String s, Delivery delivery) {
        String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
        logger.info("Received message: " + message);
        String processedMessage;
        try {
            processedMessage = requestProcessedMessage(message).replaceAll("^\"(.*)\"$", "$1");
        } catch (IOException e) {
            logger.error("Failed to get processed message email_from lambda", e);
            return;
        }
        sendEmail(processedMessage);
    }

    private String requestProcessedMessage(String message) throws IOException {
        HttpPost post = new HttpPost(lambda_url);
        StringEntity myEntity = new StringEntity(message, ContentType.create("text/plain", "UTF-8"));
        post.setEntity(myEntity);
        String result;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)){
            result = EntityUtils.toString(response.getEntity());
        }

        return result;
    }

    private void sendEmail(String text) {
        logger.info("Sending email");
        Email email = EmailBuilder.startingBlank()
                .from(email_from)
                .to(email_to)
                .withSubject(email_subj)
                .withHTMLText(text)
                .buildEmail();

        Mailer mailer = MailerBuilder
                .withSMTPServer(smtp, 587, smtp_user, smtp_password)
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withSessionTimeout(10 * 1000)
                .clearEmailAddressCriteria() // turns off email validation
                .withDebugLogging(true)
                .buildMailer();

        mailer.sendMail(email);
        logger.info("Message sent successfully");
    }
}
