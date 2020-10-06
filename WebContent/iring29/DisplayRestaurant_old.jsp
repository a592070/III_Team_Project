<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome</title>
    <style>
        * {
            padding: 0;
            margin: 0;
        }

        .box {
            width: 800px;
            margin: 50px auto;
        }

        div {
            border: 1px solid black;
        }

        h3 {
            text-align: center;
        }

        img {
            width: 750px;
            height: 200px;
            border: 1px solid black;
            /* float: left; */
        }

        legend {
            text-align: center;
            padding: 20px;
        }

        p {
            padding: 30px;
        }

        #modify {
            padding: 15px;
            float: right;
        }
        #book{
            width: 100px;
            text-align: center;
        }
        .but{
            width: 390px;
        }
        .booking{
            text-align: center;
            overflow:hidden;
        }
        span{
            text-align: left;
        }
    </style>
</head>
<body>
<jsp:useBean id="res_data" class="iring29.bean.RestaurantBean" scope="session"></jsp:useBean>
<div class="box">
        <div>
            <h3>Welcome Homepage</h3>
        </div>
            <div>
                <div class="booking">
                    <img src="<jsp:getProperty name="res_data" property="picture"/>" alt=""> 
                    <div>
                    </div>
                    <div>
                        <button class="but">BOOKING</button>
                    </div>
                    
                </div>
                <!-- <p>Personal Info</p> -->
                <p>
                    <td>Name</td>
                    <td><jsp:getProperty name="res_data" property="name"/></td>
                </p>
                <p>type</p> <jsp:getProperty name="res_data" property="type"/>
                <p>Rating</p> <jsp:getProperty name="res_data" property="rating"/>
                <hr>
                <p>tel</p> <jsp:getProperty name="res_data" property="tel"/>
                <hr>
                <p>description</p> <jsp:getProperty name="res_data" property="description"/>
                <p>opentime</p> <jsp:getProperty name="res_data" property="opentime"/>
                <hr>
                <p>address</p> <jsp:getProperty name="res_data" property="address"/>
                <p>transportation</p> <jsp:getProperty name="res_data" property="transportation"/>
                <hr>
                <p>serviceInfo</p> <jsp:getProperty name="res_data" property="serviceinfo"/>
            </div>
            <a href="iring29/Restaurant_index.jsp">Home Page</a>
        </fieldset>
    </div>
</body>
</html>