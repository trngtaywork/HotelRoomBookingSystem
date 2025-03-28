package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class SendEmailServlet extends HttpServlet {

    private static final String EMAIL = "trngtay.work@gmail.com"; 
    private static final String PASSWORD = "gimq hsof eglx gcre"; 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String toEmail = request.getParameter("email");
        String subject = request.getParameter("subject");
        String messageContent = request.getParameter("message");

        // Cấu hình SMTP Server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587"); 
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageContent);

            Transport.send(message);

            request.setAttribute("successMessage", "Email sent successfully to " + toEmail);
        } catch (MessagingException e) {
            request.setAttribute("errorMessage", "Error: " + e.getMessage());
            e.printStackTrace();
        }

        request.getRequestDispatcher("sendEmail.jsp").forward(request, response);
    }
}
