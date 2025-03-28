package servlet;

import dao.FeedbackDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Feedback;
import java.io.IOException;
import java.util.List;

public class FeedbackListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        List<Feedback> feedbackList = feedbackDAO.getAllFeedbacks();
        request.setAttribute("feedbackList", feedbackList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedbackList.jsp");
        dispatcher.forward(request, response);
    }
}
