<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hotel Booking | Change Password</title>

        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">

        <style>
            .change-password-section {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 80vh;
                background-color: #f5f5f5;
            }
            .change-password-form {
                background: #fff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                width: 400px;
            }
            .change-password-form h3 {
                margin-bottom: 20px;
                text-align: center;
            }
            .change-password-form button {
                background-color: #cfa671;
                border: none;
                color: white;
                font-weight: bold;
                padding: 10px;
                border-radius: 5px;
                width: 100%;
            }
        </style>
    </head>
    <body>

        <jsp:include page="includes/header.jsp" />

        <section class="change-password-section">
            <div class="change-password-form">
                <h3>Change Password</h3>
                <% 
                    String message = (String) request.getAttribute("message");
                    if (message != null) {
                %>
                <p style="color: red; text-align: center;"><%= message %></p>
                <% } %>
                <form action="ChangePasswordServlet" method="post">
                    <div class="form-group">
                        <label for="currentPassword">Current Password</label>
                        <input type="password" id="currentPassword" name="currentPassword" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="newPassword">New Password</label>
                        <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Confirm New Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                    </div>
                    <button type="submit" class="btn">Change Password</button>
                </form>
            </div>
        </section>

        <jsp:include page="includes/footer.jsp" />

        <script src="<%= request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath() %>/js/main.js"></script>
    </body>
</html>
