package servlet;

import dao.ProfileDAO;
import model.Profile;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class EditProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProfileDAO profileDAO;

    public EditProfileServlet() {
        this.profileDAO = new ProfileDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountID = Integer.parseInt(request.getParameter("accountID"));
        String name = request.getParameter("name").trim();
        String phone = request.getParameter("phone").trim();
        String gender = request.getParameter("gender");
        String address = request.getParameter("address").trim();

        boolean hasError = false;

        if (name.isEmpty()) {
            request.setAttribute("nameError", "Full Name is required.");
            hasError = true;
        }

        if (!isValidPhone(phone)) {
            request.setAttribute("phoneError", "Invalid phone number.");
            hasError = true;
        }

        if (address.isEmpty()) {
            request.setAttribute("addressError", "Address is required.");
            hasError = true;
        }

        if (hasError) {
            request.getRequestDispatcher("editProfile.jsp").forward(request, response);
            return;
        }

        Profile profile = new Profile(0, name, phone, gender, address, accountID);
        boolean isUpdated = profileDAO.updateProfile(profile);

        if (isUpdated) {
            request.setAttribute("successMessage", "Profile updated successfully.");
        } else {
            request.setAttribute("errorMessage", "Failed to update profile.");
        }

        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }

    private boolean isValidPhone(String phone) {
        return Pattern.matches("^\\d{10,11}$", phone);
    }
}
