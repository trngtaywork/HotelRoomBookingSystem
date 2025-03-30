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

        // Lấy các tham số lọc từ request
        String roomName = request.getParameter("roomName");
        String ratingStr = request.getParameter("rating");
        String dateStr = request.getParameter("date");

        // Chuyển đổi Rating sang số nguyên
        Integer rating = (ratingStr != null && !ratingStr.isEmpty()) ? Integer.parseInt(ratingStr) : null;

        // Gọi phương thức DAO với các tham số lọc
        List<Feedback> feedbackList = feedbackDAO.getFeedbacksWithFilters(roomName, rating, dateStr);

        // Đặt feedbackList vào request
        request.setAttribute("feedbackList", feedbackList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("feedbackList.jsp");
        dispatcher.forward(request, response);
    }

}
