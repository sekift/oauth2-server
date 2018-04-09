<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<%@ page isELIgnored="false" %>
<head>
    <meta charset="utf-8">
    <title>xx授权</title>

    <!-- Sets initial viewport load and disables zooming  -->
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">

    <!-- Makes your prototype chrome-less once bookmarked to your phone's home screen -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!-- Set a shorter title for iOS6 devices when saved to home screen -->
    <meta name="apple-mobile-web-app-title" content="oauth touch">

    <!-- Set Apple icons for when prototype is saved to home screen -->
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://www.oauthui.com/m/touch/images/icons/apple-touch-icon-114x114.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://www.oauthui.com/m/touch/images/icons/apple-touch-icon-72x72.png"/>
    <link rel="apple-touch-icon-precomposed" sizes="57x57" href="http://www.oauthui.com/m/touch/images/icons/apple-touch-icon-57x57.png"/>
    <link rel="apple-touch-startup-image" href="http://www.oauthui.com/m/touch/images/startup.jpg">
    <!--<link rel="icon" type="image/x-icon" href="favicon.ico" />-->

    <!-- Include the CSS -->
    <link rel="stylesheet" href="http://www.oauthui.com/m/css/login.css"/>
</head>
<body>

<!-- header -->
<header class="header-www clearfix">
    <img src="http://www.oauthui.com/m/images/nav_top_logo.gif" alt="logo" />
</header>
<!-- .content -->
<div class="content" id="page_login">
	<div class="set" id="set" <c:if test="${ online }"> style="display:none"</c:if>>
		<form method="post" id="form" action="https://www.oauth.com/login">
			<input type="hidden" name="__sid" id="__sid" value="" />
			<!-- fowardURL是成功时返回的，而returnURL是失败时返回的 -->
			<input type="hidden" name="fowardURL" id="fowardURL" value="${ forwarURL }" />
			<input type="hidden" name="returnURL" id="returnURL" value="${ returnURL }" />
           <div class="form-item item-with-label rel">
               <label>用户名/手机号/邮箱</label>
               <input class="border" type="text" name="vwriter" id="user_name_set"/>
               <p class="tips"></p>
           </div>
           <div class="form-item item-with-label rel">
               <label>密　码</label>
               <input class="border" type="password" name="vpassword" id="password_set"/>
               <p class="tips"></p>
           </div>
           <div class="form-item align-center clearfix">
               <a href="http://www.oauth.com/index_m.jsp">忘记密码？</a>
               <a class="fr" href="https://www.oauth.com/m/register/default.jsp?fowardURL=${ registerForwardURL }">注册新账号</a>
           </div>
           <div class="form-control"><button class="btn_blue" type="submit">登录</button></div>
    	</form>
	</div>
	<c:if test="${ online }">
	<div class="auto" id="auto">
	    <a href="${ forwarURL }" title="登录" class="block auto-content">
	        <img src="http://www.oauthui.com/${ userId }" alt="头像" />
	    </a>
	    <p>${userName}</p>
	    <div class="form-control">
	    <a class="btn_blue" href="${ forwarURL }">登录</a>
	    <div class="changeId" id="changeId">更换账号</div>
	    </div>
	</div>
	</c:if>
	<p class="nomar"><span>${clientName}</span> 将获得以下授权：</p>
	<p class="clearfix nomar"><i class="fl"></i>用户名、头像以及基本资料</p>
</div>
<!-- Include the JS -->
<script src="http://www.oauthui.com/login.js"></script>
<c:if test="${ not empty error }">
<script type="text/javascript">
	alert('${error}');
</script>
</c:if>
</body>
</html>