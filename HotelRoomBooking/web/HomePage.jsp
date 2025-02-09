<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hotel Booking | Home</title>

        <!-- CSS Styles -->
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css" type="text/css">

        <style>
            .hero-section {
                background-image: url('<%= request.getContextPath() %>/img/hero/hero-1.jpg');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
                position: relative;
            }
            .booking-form {
                position: absolute;
                top: 50%;
                right: 10%;
                transform: translateY(-50%);
                background: rgba(255, 255, 255, 0.9);
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                color: black;
                max-width: 350px;
            }
            .booking-form input,
            .booking-form select,
            .booking-form button {
                width: 100%;
                margin-bottom: 10px;
            }
            .booking-form button {
                background-color: #cfa671;
                border: none;
                color: white;
                font-weight: bold;
                padding: 10px;
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="includes/header.jsp" />

        <section class="hero-section">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="hero-text">
                            <h1>Sona A Luxury Hotel</h1>
                            <p>Here are the best hotel booking sites, including recommendations for international travel and finding low-priced hotel rooms.</p>
                            <a href="#" class="primary-btn">Discover Now</a>
                        </div>
                    </div>
                    <div class="col-lg-5 offset-lg-1">
                        <div class="booking-form">
                            <h3>Booking Your Hotel</h3>
                            <form action="#">
                                <div class="check-date">
                                    <label for="date-in">Check In:</label>
                                    <input type="date" class="date-input" id="date-in">
                                    <i class="icon_calendar"></i>
                                </div>
                                <div class="check-date">
                                    <label for="date-out">Check Out:</label>
                                    <input type="date" class="date-input" id="date-out">
                                    <i class="icon_calendar"></i>
                                </div>
                                <div class="select-option">
                                    <label for="guest">Guests:</label>
                                    <select id="guest">
                                        <option value="">1 Adults</option>
                                        <option value="">2 Adults</option>
                                        <option value="">3 Adults</option>
                                        <option value="">4 Adults</option>
                                        <option value="">5 Adults</option>
                                        <option value="">6 Adults</option>

                                    </select>
                                </div>
                                <div class="select-option">
                                    <label for="room">Room:</label>
                                    <select id="room">
                                        <option value="">1 Room</option>
                                        <option value="">2 Room</option>
                                        <option value="">3 Room</option>
                                        <option value="">4 Room</option>
                                    </select>
                                </div>
                                <button type="submit" class="primary-btn">Check Availability</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- About Us Section -->
        <section class="aboutus-section spad">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="about-text">
                            <div class="section-title">
                                <span>About Us</span>
                                <h2>Intercontinental LA <br />Westlake Hotel</h2>
                            </div>
                            <p>Sona.com is a leading online accommodation site...</p>
                            <a href="#" class="primary-btn about-btn">Read More</a>
                        </div>
                    </div>

                </div>
            </div>
        </section>

        <!-- Include Footer -->
        <jsp:include page="includes/footer.jsp" />

        <!-- JavaScript -->
        <script src="<%= request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
        <script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>
        <script src="<%= request.getContextPath() %>/js/main.js"></script>
    </body>
</html>