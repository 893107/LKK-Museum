/* ajax
 - javascript에서 서버와 브라우저가 비동기 통신을 하게 도와주는 기능
 - 웹 페이지를 다시 로딩하지 않고 데이터를 불러올 수 있게 해줌
*/

//회원가입
function idcheck(){
    console.log('함수 실행');
    let username = $('input#username').val();
    console.log(username);
    if(username != ''){ 
        $.ajax({
            url:'/auth/idCheck', 
            type:'GET', 
            data: {username: username},
            success:function(cnt){ 
                if(cnt == 0){ 
                    document.getElementById('printmsg1').innerHTML = '&emsp;사용 가능한 아이디 입니다.';
                    document.getElementById("username").readOnly = true;
                    document.getElementById("check1").value = 'Y';
                } else { 
                    document.getElementById('printmsg1').innerHTML = '&emsp;이미 사용중인 아이디 입니다.';
                    document.getElementById("check1").value = 'N';
                }
            },
            error:function(){
                document.getElementById('printmsg1').innerHTML = '&emsp;오류가 발생했습니다.';
                comsole.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    } else {
        document.getElementById('printmsg1').innerHTML = '&emsp;아이디를 입력해주세요.';
        document.getElementById("check1").value = 'N';
    }
};

function pwcheck() {
    console.log('함수 실행');
     let p1 = $('input#password').val();
     let p2 = $('input#password2').val();
    if(p1 != '' && p2 != '') {
        if( p1 != p2 ) {
            document.getElementById('printmsg2').innerHTML = '&emsp;비밀번호가 일치하지 않습니다. 다시 확인해주세요.';
            document.getElementById("check2").value = 'N';
        } else {
            document.getElementById('printmsg2').innerHTML = '&emsp;비밀번호가 일치합니다';
            document.getElementById("check2").value = 'Y';
          }
     } else {
    document.getElementById('printmsg2').innerHTML = '&emsp;비밀번호를 입력해주세요.';
    document.getElementById("check2").value = 'N';
    }
}

function emailcheck(){
    console.log('함수 실행');
    let email = $('input#email').val();
    console.log(email);
    if(email != ''){
        $.ajax({
            url:'/auth/emailCheck', 
            type:'GET', 
            data: {email: email},
            success:function(cnt){ 
                if(cnt == 0){ 
                    document.getElementById('printmsg3').innerHTML = '&emsp;사용 가능한 이메일 입니다.';
                    document.getElementById("check3").value = 'Y';
                } else { 
                    document.getElementById('printmsg3').innerHTML = '&emsp;이미 사용중인 이메일 입니다.';
                    document.getElementById("check3").value = 'N';
                }
            },
            error:function(){
                document.getElementById('printmsg3').innerHTML = '&emsp;오류가 발생했습니다.'
                comsole.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    } else {
        document.getElementById('printmsg3').innerHTML = '&emsp;이메일을 입력해주세요.';
        document.getElementById("check3").value = 'N';
    }
};

function datainfo(){
    console.log('함수 실행');
    let form = $("#contactform");
    let email = $('input#email').val();
    
    if(document.getElementById("check1").value == 'Y'){
        if(document.getElementById("check2").value == 'Y'){
            if(document.getElementById("check3").value == 'Y'){
                document.getElementById('printmsg4').innerHTML = '';
                $.ajax({
                    url:'/auth/mailsend', 
                    type:'GET', 
                    data: {email: email},
                    success:function(){ 
                    	alter("succcess");
                    },
                    error:function(){
                        alter("Error");
                    }
                });                     
                form.submit();
            } else {
                document.getElementById('printmsg4').innerHTML = '&emsp;이메일 중복 체크를 실행해주세요.';
            }
        } else {
            document.getElementById('printmsg4').innerHTML = '&emsp;비밀번호 확인을 실행해주세요.';
        }
    } else {
        document.getElementById('printmsg4').innerHTML = '&emsp;아이디 중복 확인을 실행해주세요.';
    }
};

//회원 정보 수정
function update(){
    let Display= document.getElementById("email");
    if(Display.readOnly == true) {
        document.getElementById('password').type = 'password'
        document.getElementById('password2').type = 'password'
        document.getElementById('checkpw').type = 'button'
        document.getElementById('checkemail').type = 'text'
        document.getElementById('btn-update').type = 'button'
        document.getElementById('pwdiv').style = 'display:block'
        document.getElementById('printmsg2').style = 'display:block'
        document.getElementById('printmsg3').style = 'display:block'
        document.getElementById('printmsg4').style = 'display:block'
   
        document.getElementById('email').readOnly = false
        document.getElementById('phone').readOnly = false 

    } else if(Display.readOnly == false) {
        document.getElementById('password').type = 'hidden'
        document.getElementById('password2').type = 'hidden'
        document.getElementById('checkpw').type = 'hidden'
        document.getElementById('checkemail').type = 'hidden'
        document.getElementById('btn-update').type = 'hidden'
        document.getElementById('pwdiv').style = 'display:none'
        document.getElementById('printmsg2').style = 'display:none'
        document.getElementById('printmsg3').style = 'display:none'
        document.getElementById('printmsg4').style = 'display:none'
   
        document.getElementById('email').readOnly = true
        document.getElementById('phone').readOnly = true
    }
}

function dataupdate(){
    console.log('함수 실행');
    let form = $("#mypageform");
   
    if(document.getElementById("check2").value == 'Y'){
          if(document.getElementById("check3").value == 'Y'){
            document.getElementById('printmsg4').innerHTML = '';
            form.submit();
         } else {
            document.getElementById('printmsg4').innerHTML = '&emsp;이메일 중복 체크를 실행해주세요.';
        }
    } else {
         document.getElementById('printmsg4').innerHTML = '&emsp;비밀번호 확인을 실행해주세요.';
    }
};

function sendcolumn(){
    console.log('함수 실행');
    let email = $('input#email').val();
    $.ajax({
        url:'/auth/mailsend2', 
        type:'GET', 
        data: {email: email},
        success:function(){ 
        },
        error:function(){
        }
    });                     
};


//레이어 팝업
function showlayer(){
    document.getElementById('layer_bg').style = 'display:block';
    $("#layer_bg").css({
        "top": (($(window).height()-$("#layer_bg").outerHeight())/2+$(window).scrollTop())+"px",
        "left": (($(window).width()-$("#layer_bg").outerWidth())/2+$(window).scrollLeft())+"px"
    });
    $("body").css("overflow","hidden");
}

function showlayer2(){
    document.getElementById('layer_bg2').style = 'display:block';
    $("#layer_bg2").css({
        "top": (($(window).height()-$("#layer_bg2").outerHeight())/2+$(window).scrollTop())+"px",
        "left": (($(window).width()-$("#layer_bg2").outerWidth())/2+$(window).scrollLeft())+"px"
    });
    $("body").css("overflow","hidden");
}

$(document).mouseup(function (e){
    var LayerPopup = $("#layer_bg");
    if(LayerPopup.has(e.target).length === 0){
        document.getElementById('layer_bg').style = 'display:none';
        $("body").css("overflow","scroll");
    }
});

function closemoney(){
    document.getElementById('layer_bg2').style = 'display:none';
    $("body").css("overflow","scroll");
}

//카운트다운 타이머
function makeTimer() {

    var endTime = new Date("July 22, 2022 18:00:00 PDT");			
    var endTime = (Date.parse(endTime)) / 1000;

    var now = new Date();
    var now = (Date.parse(now) / 1000);

    var timeLeft = endTime - now;

    var days = Math.floor(timeLeft / 86400); 
    var hours = Math.floor((timeLeft - (days * 86400)) / 3600);
    var minutes = Math.floor((timeLeft - (days * 86400) - (hours * 3600 )) / 60);
    var seconds = Math.floor((timeLeft - (days * 86400) - (hours * 3600) - (minutes * 60)));

    if (hours < "10") { hours = "0" + hours; }
    if (minutes < "10") { minutes = "0" + minutes; }
    if (seconds < "10") { seconds = "0" + seconds; }

    $("#days").html(days + "<span> : </span>");
    $("#hours").html(hours + "<span> : </span>");
    $("#minutes").html(minutes + "<span> : </span>");
    $("#seconds").html(seconds);		

}

setInterval(function() { makeTimer(); }, 1000);

$(document).ready(function(){
    var currentPosition = parseInt($(".go-to-top").css("top"));
    $(window).scroll(function() {
      var position = $(window).scrollTop(); 
      $(".quickmenu").stop().animate({"top":position+currentPosition+"px"},1000);
    });
});