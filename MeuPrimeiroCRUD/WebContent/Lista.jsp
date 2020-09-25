<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 
 <html>
	<head>
		<title>Exemplo de JSP</title>
	</head>
	<body>
		<% String mensagem = "uma mensagem sendo inserida no html"; %>
		<% out.println(mensagem); %>
		<br />
		<% String mensagem2 = "outra forma de inserir texto"; %>
		<%= mensagem2 %>
		<br />
		<% System.out.println("Esse texto só aparece no console do Tomcat!"); %>
	</body>
</html>