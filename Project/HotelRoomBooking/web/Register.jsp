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

    <style>
        body{
            margin:0;
            color:#6a6f8c;
            background:#c8c8c8;
            font:600 16px/18px 'Open Sans',sans-serif;
        }
        *,:after,:before{
            box-sizing:border-box
        }
        .clearfix:after,.clearfix:before{
            content:'';
            display:table
        }
        .clearfix:after{
            clear:both;
            display:block
        }
        a{
            color:inherit;
            text-decoration:none
        }

        .login-wrap{
            width:100%;
            margin:auto;
            max-width:525px;
            min-height:670px;
            position:relative;
            background:url(https://raw.githubusercontent.com/khadkamhn/day-01-login-form/master/img/bg.jpg) no-repeat center;
            box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
        }
        .login-html{
            width:100%;
            height:100%;
            position:absolute;
            padding:90px 70px 50px 70px;
            background:rgba(40,57,101,.9);
        }
        .login-html .sign-in-htm,
        .login-html .sign-up-htm{
            top:0;
            left:0;
            right:0;
            bottom:0;
            position:absolute;
            transform:rotateY(180deg);
            backface-visibility:hidden;
            transition:all .4s linear;
        }
        .login-html .sign-in,
        .login-html .sign-up,
        .login-form .group .check{
            display:none;
        }
        .login-html .tab,
        .login-form .group .label,
        .login-form .group .button{
            text-transform:uppercase;
        }
        .login-html .tab{
            font-size:22px;
            margin-right:15px;
            padding-bottom:5px;
            margin:0 15px 10px 0;
            display:inline-block;
            border-bottom:2px solid transparent;
        }
        .login-html .sign-in:checked + .tab,
        .login-html .sign-up:checked + .tab{
            color:#fff;
            border-color:#1161ee;
        }
        .login-form{
            min-height:345px;
            position:relative;
            perspective:1000px;
            transform-style:preserve-3d;
        }
        .login-form .group{
            margin-bottom:15px;
        }
        .login-form .group .label,
        .login-form .group .input,
        .login-form .group .button{
            width:100%;
            color:#fff;
            display:block;
        }
        .login-form .group .input,
        .login-form .group .button{
            border:none;
            padding:15px 20px;
            border-radius:25px;
            background:rgba(255,255,255,.1);
        }
        .login-form .group input[data-type="password"]{
            text-security:circle;
            -webkit-text-security:circle;
        }
        .login-form .group .label{
            color:#aaa;
            font-size:12px;
        }
        .login-form .group .button{
            background:#1161ee;
        }
        .login-form .group label .icon{
            width:15px;
            height:15px;
            border-radius:2px;
            position:relative;
            display:inline-block;
            background:rgba(255,255,255,.1);
        }
        .login-form .group label .icon:before,
        .login-form .group label .icon:after{
            content:'';
            width:10px;
            height:2px;
            background:#fff;
            position:absolute;
            transition:all .2s ease-in-out 0s;
        }
        .login-form .group label .icon:before{
            left:3px;
            width:5px;
            bottom:6px;
            transform:scale(0) rotate(0);
        }
        .login-form .group label .icon:after{
            top:6px;
            right:0;
            transform:scale(0) rotate(0);
        }
        .login-form .group .check:checked + label{
            color:#fff;
        }
        .login-form .group .check:checked + label .icon{
            background:#1161ee;
        }
        .login-form .group .check:checked + label .icon:before{
            transform:scale(1) rotate(45deg);
        }
        .login-form .group .check:checked + label .icon:after{
            transform:scale(1) rotate(-45deg);
        }
        .login-html .sign-in:checked + .tab + .sign-up + .tab + .login-form .sign-in-htm{
            transform:rotate(0);
        }
        .login-html .sign-up:checked + .tab + .login-form .sign-up-htm{
            transform:rotate(0);
        }

        .hr{
            height:2px;
            margin:60px 0 50px 0;
            background:rgba(255,255,255,.2);
        }
        .foot-lnk{
            text-align:center;
        }
    </style>

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
        <!--
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
        -->

        <div class="login-wrap">
            <div class="login-html">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
                <div class="login-form">
                    <div class="sign-in-htm">
                        <div class="group">
                            <label for="user" class="label">Username</label>
                            <input id="user" type="text" class="input">
                        </div>
                        <div class="group">
                            <label for="pass" class="label">Password</label>
                            <input id="pass" type="password" class="input" data-type="password">
                        </div>
                        <div class="group">
                            <input id="check" type="checkbox" class="check" checked>
                            <label for="check"><span class="icon"></span> Keep me Signed in</label>
                        </div>
                        <div class="group">
                            <input type="submit" class="button" value="Sign In">
                        </div>
                        <div class="hr"></div>
                        <div class="foot-lnk">
                            <a href="#forgot">Forgot Password?</a>
                        </div>
                    </div>
                    <form method="post" action="Register">
                        <div class="sign-up-htm">
                            <div class="group">
                                <label for="user" class="label">Username</label>
                                <input id="user" name="username" type="text" class="input">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Email Address</label>
                                <input id="pass" name="email" type="text" class="input">
                            </div>
                            <div class="group">
                                <label for="pass" class="label">Password</label>
                                <input id="pass" name="password" type="password" class="input" data-type="password">
                            </div>

                            <div class="group">
                                <input type="submit" class="button" value="Sign Up">
                            </div>
                            <div class="hr"></div>
                            <div class="foot-lnk">
                                <label for="tab-1">Already Member?</a>
                            </div>
                        </div>
                    </form>>
                </div>
            </div>
        </div>

        <footer>
            <jsp:include page="footer.html"></jsp:include>
        </footer>
    </body>
</html>
