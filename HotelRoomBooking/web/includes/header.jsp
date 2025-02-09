<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>

<header class="header-section">
  <div class="menu-item">
    <div class="container">
      <div class="row">
        <div class="col-lg-2">
          <div class="logo">
            <a href="<%= request.getContextPath() %>/index.jsp">
              <img src="<%= request.getContextPath() %>/img/room/logo.png" alt="Logo">
            </a>
          </div>
        </div>
        <div class="col-lg-10">
          <div class="nav-menu">
            <nav class="mainmenu">
              <ul>
                <li class="active"><a href="<%= request.getContextPath() %>/index.jsp">Home</a></li>
                <li><a href="<%= request.getContextPath() %>/rooms.jsp">Rooms</a></li>
                <li><a href="<%= request.getContextPath() %>/about-us.jsp">About Us</a></li>
                <li><a href="#">Pages</a>
                  <ul class="dropdown">
                    <li><a href="<%= request.getContextPath() %>/room-details.jsp">Room Details</a></li>
                    <li><a href="<%= request.getContextPath() %>/blog-details.jsp">Blog Details</a></li>
                    <li><a href="#">Family Room</a></li>
                    <li><a href="#">Premium Room</a></li>
                  </ul>
                </li>
                <li><a href="<%= request.getContextPath() %>/blog.jsp">News</a></li>
                <li><a href="<%= request.getContextPath() %>/contact.jsp">Contact</a></li>
              </ul>
            </nav>
            <div class="nav-right search-switch">
              <i class="icon_search"></i>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</header>
