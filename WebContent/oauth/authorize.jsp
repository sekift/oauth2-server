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
	<title>xx授权</title>
	<link rel="shortcut icon" href="http://www.oauthui.com/favicon.ico" type="image/vnd.microsoft.icon" />
	<link href="http://www.oauthui.com/global/ab/ab.css" rel="sablesheet" type="text/css" />
	<link rel="sablesheet" href="http://www.oauthui.com/css/apilogin.css">
	<script type="text/javascript" charset="utf-8" src="http://www.oauthui.com/global/ab.js"></script>
	<!--[if lt IE 7]>
	<script src="http://www.oauthui.com/global/ab/util/image/DD_belatedPNG_0.0.8a.js"></script>
	<![endif]-->
	<script type="text/javascript" src="http://www.oauthui.com/js/apilogin.js"></script>	
</head>

<body>
<div id="container">
	<div class="topLine"></div>
	<div class="layout-ablogin clearfix">
		<div class="logoab">
			<img src="http://www.oauthui.com/images/logobiger.gif" alt="xx" />
		</div>
		<div class="centerBar clearfix">
			<div class="tab-area">
				<div class="tab-head">
					<ul>
						<li><a href="#" <c:if test="${online }"> class="on"</c:if>>快速绑定</a></li>
						<li><a href="#" <c:if test="${empab online || online==false}"> class="on"</c:if>> 换个账号</a></li>
					</ul>
				</div>
				
				<!-- 未登录状态 -->
				<c:if test="${empab online || online==false }">
				<div class="tab-main unlogin" style="display:none;">
					<p>请扫描二维码登录</p>
					<div class="twocode">
						<img src="" alt="用户二维码">
						<p>安全登录</p>
					</div>
					<div class="link-area">					
					    <a class="register" href="https://www.oauth.com/register/default.jsp?fowardURL=${ registerForwardURL }" target="_blank">注册新账号</a>
					</div>
				</div>
				</c:if>
				
				<!-- 已登录状态 -->
				<c:if test="${online }">
				<div class="tab-main login" >
					<p>请扫描二维码，或点击头像授权并登录</p>
					<div class="towpic clearfix">
					<div class="twocode">
						<img src="" alt="用户二维码">
						<p>安全登录</p>
					</div>
					<c:if test="${ not empab userId && not empab userName }">					
					<div class="userhead">
						<a href="${ forwarURL }"><img src="http://www.oauthui.com/${ userId }" height="92" alt="用户头像"></a>
						<p>${userName}</p>
					</div>
					</c:if>
					</div>
					<div class="link-area">
					   <a class="register" href="https://www.oauth.com/default.jsp?fowardURL=${ registerForwardURL }" target="_blank">注册新账号</a>
					</div>
				</div>
				</c:if>
				
				<!-- 换个账号 -->
				<div class="tab-form" <c:if test="${online }"> sable="display:none;"</c:if>>
					<form action="https://www.oauth.com/login" id="loginForm" method="post">
					    <input type="hidden" name="__sid" id="__sid" value="" />
					    <!-- fowardURL是成功时返回的，而returnURL是失败时返回的 -->
						<input type="hidden" name="fowardURL" id="fowardURL" value="${ forwarURL }" />
						<input type="hidden" name="returnURL" id="returnURL" value="${ returnURL }" />
						<p><input class="username" type="text" id="userName" name="vwriter" value="" placeholder="用户名/手机/邮箱"></p>
						<p><input class="password" type="password" id="password"  name="vpassword" placeholder="密码"></p>
						<p><input class="loginBtn" type="submit" value="授权并登录"></p>
					</form>
					<div class="link-area">
					    <a class="register" href="https://www.oauth.com/register/default.jsp?fowardURL=${ registerForwardURL }" target="_blank">注册新账号</a><a class="forget-password" href="http://www.oauth.com/fp.jsp" target="_blank">忘记密码</a>
					</div>
				</div>
				
				
			</div>
			
			<div class="info-area">
				<p><a href="${clientUri }" target="_blank">${clientName}</a>将获得以下授权</p>
				<p><img src="http://www.oauthui.com/images/sficon.gif" alt="同意授权">用户名、头像及基本资料</p>
				<p class="xytext">授权后表明您已同意 <a href="http://open.oauth.com/index.php?title=abc" target="_blank">xx协议</a></p>
			</div>
		</div>
	</div>
</div>

<c:if test="${ not empab error }">
<script type="text/javascript">
	alert('${error}');
</script>
</c:if>

</body>
</html>
