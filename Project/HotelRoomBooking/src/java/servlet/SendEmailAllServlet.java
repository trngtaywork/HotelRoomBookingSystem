package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.AccountDAO;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import model.Account;

public class SendEmailAllServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String role = request.getParameter("role");
        String subject = request.getParameter("subject");
        String messageContent = request.getParameter("message");

        AccountDAO accountDAO = new AccountDAO();
        List<Account> usersToSendEmail;

        if ("All".equals(role)) {
            usersToSendEmail = accountDAO.getAllAccounts();
        } else {
            usersToSendEmail = accountDAO.getUsersByRole(role);
        }

        for (Account account : usersToSendEmail) {
            String toEmail = account.getEmail();
            sendEmail(toEmail, subject, messageContent);
        }

        request.setAttribute("role", role);

        request.setAttribute("successMessage", "Email đã được gửi thành công đến các " + role);
        request.getRequestDispatcher("sendEmailAll.jsp").forward(request, response);
    }

    private void sendEmail(String toEmail, String subject, String messageContent) {
        String fromEmail = "trngtay.work@gmail.com";
        String password = "gimq hsof eglx gcre";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); 
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Tạo session email
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject); 
            message.setText(messageContent);

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Email sending failed.");
        }
    }
}
