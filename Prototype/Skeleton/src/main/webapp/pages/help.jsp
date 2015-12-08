<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="org.sample.model.User" %>

<c:import url="template/header.jsp" />

<html>
<head>
<title>A little help</title>
<link rel="stylesheet" type="text/css" href="/Skeleton/css/helpPageStyle.css">
</head>

	<h2>A little help</h2>
		<div class="help" id="help-starter">
	   		 <h4>Hey ${user.firstName}! Welcome on our "help"-page</h4>
	   		 <p>We would like to give you some useful information about the service we provide.</p>
    	</div>
    	<div class="help" id="help-about">
    		<h4>What is it about ?</h4>
    		<p>Basically, we want to bring students together, so that one gets help concerning a <strong>specific course</strong> and the other gets a little something for helping out.</p>
       		<p>As you see you can either learn something as a student or you can earn a bit money by teaching as a tutor.</p>
    	</div>
    	
    	<div class="help" id="help-search">
    		<h4>How to search for a tutor ?</h4>
    		<p>Click on <strong>Search</strong> in the header and search a tutor.</p>
    		<p>You can filter the search by indicating an university, a subject and a course.</p>
    		<p>You can even search for tutors which had a specific grade by clicking on "More-criteria".</p>
        	<p>Hint: You don't got to specify anything if you want to search for all the available courses</p>
    	</div>
    	
    	<div class="help" id="help-request">
    		<h4>How to contact a tutor ?</h4>
    		<p>If you search for a tutor you get a result list of all the matching tutors.</p>
    		<p>You can then compare the different tutors. Just click on "visit profile" and take a closer look.</p>
    		<p>If you have chosen one you can send him a request by clicking the lowest button on his profile page.</p>
    		<p>The tutor will then get your request and can accept or decline the request. If he accepts the request you will both get the contact information (email and phone number) of the other and you can arrange a learning session.</p>
    		<p>Please notice that you only can send a request for the course selected in the results page.</p>
    	</div>
    	
    	<div class="help" id="help-become-tutor">
    		<h4>How to become a tutor ?</h4>
    		<p>${user.firstName} welcome to the teaching team!</p> 
    		<p>If you want to become a tutor, just go to your profile page by clicking <strong>Profile</strong> in the header. Then click <strong>edit</strong> and add some courses to your profile. The minute you indicate, that you want to teach a course you become a tutor and the students can send you a request.</p>
    	</div>
    	
    	<div class="help" id="help-costs">
    		<h4>What about the costs ?</h4>
    		<p>If a tutor accepts a request he got to pay us a little something and will get an e-bill by mail.</p>
    	</div>
<c:import url="template/footer.jsp" />
