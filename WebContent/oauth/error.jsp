<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML>
<!--[if IE 6]><html class="ie6 lte9 lte8 lte7" lang="zh-cn"><![endif]-->
<!--[if IE 7]><html class="ie7 lte9 lte8 lte7" lang="zh-cn"><![endif]-->
<!--[if IE 8]><html class="ie8 lte9 lte8" lang="zh-cn"><![endif]-->
<!--[if IE 9]><html class="ie9 lte9" lang="zh-cn"><![endif]-->
<!--[if !(IE 6) | !(IE 7) | !(IE 8) | !(IE 9)  ]><!-->
<html lang="zh-cn"><!--<![endif]-->
<%@ page isELIgnored="false" %>
<head>
	<meta charset="utf-8">
	<title>xx授权错误页面</title>
	<link rel="shortcut icon" href="http://www.oauthui.com/favicon.ico" type="image/vnd.microsoft.icon" />
	<link href="http://www.oauthui.com/ab.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="http://www.oauthui.com/css/api.css">
	<script type="text/javascript" charset="utf-8" src="http://www.oauthui.com/ab.js"></script>
	<!--[if lt IE 7]>
	<script src="http://www.oauthui.com/image/a.js"></script>
	<![endif]-->
	<script type="text/javascript" src="http://www.oauthui.com/js/apilogin.js"></script>	
</head>

<body>
<div id="container">
	<div class="topLine"></div>
	<div class="clearfix">
		<div class="logo">
			<img src="http://www.oauthui.com/images/logobiger.gif" alt="xx" />
		</div>
		<div class="centerBar clearfix">
			<div class="tab-main">
				<p>${error}</p>
				<a href="http://open.oauth.com/index.php?title=abc">了解如何开通</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>
