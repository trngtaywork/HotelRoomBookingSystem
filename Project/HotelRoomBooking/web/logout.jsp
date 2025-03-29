<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>

<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession != null) {
        currentSession.invalidate();
    }

    response.sendRedirect("login.jsp");
%>
