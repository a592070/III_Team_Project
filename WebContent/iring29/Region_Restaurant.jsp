<%@ page import="iring29.bean.RestaurantBean" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Region Restaurant</title>

</head>
<body>
<table>
<tr>
<th>Name</th>
<th>Type</th>
<th>Region</th>
</tr>
<% List<RestaurantBean> res_data_region=((List<RestaurantBean>)request.getAttribute("res_data_region")) ; 
for(RestaurantBean res : res_data_region){
	String name = res.getName();
	String type = res.getType();
	String region = res.getRegion();
%>

<tr>
<td><%= name %></td>
<td><%= type %></td>
<td><%= region %></td>
<td><button>GO</button></td>
</tr>
<%} %>
</table>
</body>
</html>