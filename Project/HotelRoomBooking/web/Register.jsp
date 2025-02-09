<%-- 
    Document   : Register
    Created on : Jan 31, 2025, 9:52:59 PM
    Author     : My PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        
        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css?family=Lora:400,700&display=swap" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Cabin:400,500,600,700&display=swap" rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/flaticon.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
    </head>
    <body>
        <header>
            <jsp:include page="header.html"></jsp:include>
        </header>
        <!--
        <form method="post" action="Register">
            Username: <input type="text" name="username" ><br>
            Email: <input type="text" name="email" ><br>
            Password: <input type="password" name="password" ><br>
            <br>
            <%--
            Full Name: <input type="text" name="name"><br>
            Phone Number <input type="tel" name="phoneNumber"><br>
            Gender: <input type="text" name="gender"><br>
            Role <input type="text" name="role"><br>
            Address <input type="text" name="address"><br>
            <br>
            --%>
            <input type="submit" value="Register">
        </form>
        -->
            <div class="pt-5">  
            <h1 class="text-center">Register</h1>  
            <div class="container">  
                <div class="row">  
                    <div class="col-md-5 mx-auto">  
                        <div class="card card-body">  
                            <form method="post" action="Register">
                                <div class="form-group">  
                                    <h4> Enter your Username </h4>  
                                    <input type="text" class="form-control" id="username" required="" name="username" value="">  
                                </div>                      
                                <div class="form-group">  
                                    <h4> Enter your Email </h4>  
                                    <input type="text" class="form-control" id="email" required="" name="email" value="">  
                                </div>
                                <div class="form-group">  
                                    <h4> Enter your Password </h4>  
                                    <input type="password" class="form-control" id="password" required="" name="password" value="">  
                                </div>
                                <%--
                                Full Name: <input type="text" name="name"><br>
                                Phone Number <input type="tel" name="phoneNumber"><br>
                                Gender: <input type="text" name="gender"><br>
                                Role <input type="text" name="role"><br>
                                Address <input type="text" name="address"><br>
                                <br>
                                --%>
                                <div class="form-group pt-1">  
                                    <input class="btn btn-primary btn-block" type ="submit" name ="submitregister" value = "Submit">
                                </div> 
                            </form>
                        </div>  
                    </div>  
                </div>  
            </div>  
        </div>
            
        <footer>
        <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
