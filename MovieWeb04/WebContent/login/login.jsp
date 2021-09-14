<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Anime Template">
    <meta name="keywords" content="Anime, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인</title>
 <!-- Css Styles -->
<link rel="stylesheet" href="../login/css/login.css">
</head>

<body>
    <!-- Page Preloder -->
    <div id="preloder">
        <div class="loader"></div>
    </div>   
 
     <!-- Login Section Begin -->
    <section class="loginSection"> <!-- 만일을 위해 원래 클래스 이름을 적어둠. 나중에 삭제할것. class="login spad" -->
        <div class="container">
            <div class="row">
                <div><!-- class="col-lg-6" -->
                    <div class="login__form">
              
                        <div class="loginTitle">LOGIN</div>
                        <form method="post" action="login_ok.do" >
                            <div class="input__item">
                                <input type="text" name="id" placeholder="아이디" class="input__idpwBox">
                                <span class="icon_profile"></span>
                            </div>
                            <div class="input__item">
                                <input type="text" name="pwd" placeholder="비밀번호" class="input__idpwBox">
                                <span class="icon_lock"></span>
                            </div>
                            <button type="submit" class="site-btn">로그인</button>
                         </form>
                        <!--  <a href="#" class="forget_pass">비밀번호를 잊어버리셨나요?</a>-->
                    </div>
                </div>
                
            </div>
            
        </div>
    </section>
    <!-- Login Section End -->



    <!-- Js Plugins -->
    <script src="../login/js/jquery-3.3.1.min.js"></script>
    <script src="../login/js/bootstrap.min.js"></script>
    <script src="../login/js/player.js"></script>
    <script src="../login/js/jquery.nice-select.min.js"></script>
    <script src="../login/js/mixitup.min.js"></script>
    <script src="../login/js/jquery.slicknav.js"></script>
    <script src="../login/js/owl.carousel.min.js"></script>
    <script src="../login/js/main.js"></script>


</body>

</html>