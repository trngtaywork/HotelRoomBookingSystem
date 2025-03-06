<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="dao.AccountDAO" %>
<%@ page import="model.Account" %>

<%
    String accountIDStr = request.getParameter("accountID");
    if (accountIDStr == null || accountIDStr.isEmpty()) {
        response.sendRedirect("userList.jsp");
        return;
    }

    int accountID = Integer.parseInt(accountIDStr);
    AccountDAO accountDAO = new AccountDAO();
    Account account = accountDAO.getAccountById(accountID);

    if (account == null) {
        response.sendRedirect("userList.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Sona Template">
        <meta name="keywords" content="Sona, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <title>Delete User</title>
    </head>
    <body>
        <div class="container mt-4 mb-4">
            <h3>Are you sure you want to delete this user?</h3>
            <table class="table table-bordered mt-3">
                <tr>
                    <th>AccountID</th>
                    <td><%= account.getAccountID() %></td>
                </tr>
                <tr>
                    <th>Username</th>
                    <td><%= account.getUsername() %></td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td><%= account.getEmail() %></td>
                </tr>
                <tr>
                    <th>Role</th>
                    <td><%= account.getRole() %></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><%= account.getIsActive() ? "Active" : "Inactive" %></td>
                </tr>
                <tr>
                    <th>Created Date</th>
                    <td><%= account.getCreatedDate() %></td>
                </tr>
            </table>

            <form action="DeleteUserServlet" method="post">
                <input type="hidden" name="accountID" value="<%= account.getAccountID() %>">
                <button type="submit" class="btn btn-danger">Confirm Delete</button>
                <a href="userList.jsp" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </body>
</html>
