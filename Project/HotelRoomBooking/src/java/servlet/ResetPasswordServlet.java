package servlet;

import dao.AccountDAO;
import model.Account;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        AccountDAO accountDAO = new AccountDAO();
        Account user = accountDAO.getUserByEmail(email);

        if (user == null) {
            request.setAttribute("message", "Account unavailable!");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }

        // Generate new random password
        String newPassword = generateRandomPassword(8);
        
        // Update password in database
        boolean updateSuccess = accountDAO.updatePassword(email, newPassword);
        if (updateSuccess) {
            // Gửi email thông báo với mật khẩu mới
            String subject = "Password Reset";
            String messageContent = "Your new password is: " + newPassword;
            
            // Cấu hình gửi email
            String fromEmail = "trngtay.work@gmail.com"; // Tài khoản gửi email
            String fromPassword = "gimq hsof eglx gcre"; // Mật khẩu của tài khoản gửi email
            sendEmail(fromEmail, fromPassword, email, subject, messageContent); // Gửi email

            request.setAttribute("message", "New password sent to your email.");
        } else {
            request.setAttribute("message", "Error updating password. Please try again.");
        }

        request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
    }

    // Generate a random password
    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[length];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes).substring(0, length);
    }

    // Phương thức gửi email
    private void sendEmail(String fromEmail, String fromPassword, String toEmail, String subject, String messageContent) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Thiết lập Session cho việc gửi email
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, fromPassword);
            }
        });

        try {
            // Tạo đối tượng Message để gửi
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageContent);

            // Gửi email
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
