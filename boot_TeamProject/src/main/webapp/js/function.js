/* ajax
 - javascript에서 서버와 브라우저가 비동기 통신을 하게 도와주는 기능
 - 웹 페이지를 다시 로딩하지 않고 데이터를 불러올 수 있게 해줌
*/

function idcheck(){
    console.log('함수 실행');
    let id = $('input#idinfo').val();
    console.log(id);
    if(id != ''){ //아이디 미입력시 버튼 클릭 검증
        $.ajax({
            url:'/idCheck', 
            type:'POST', 
            data: {id: id},
            success:function(cnt){ 
                if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                    document.getElementById('printmsg1').innerHTML = '사용 가능한 아이디 입니다.';
                    document.getElementById("idinfo").readOnly = true;
                    document.getElementById("check1").value = 'Y';
                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                    document.getElementById('printmsg1').innerHTML = '이미 사용중인 아이디 입니다.';
                    document.getElementById("check1").value = 'N';
                }
            },
            error:function(){
                document.getElementById('printmsg1').innerHTML = '오류가 발생했습니다.';
                comsole.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    } else {
        document.getElementById('printmsg1').innerHTML = '아이디를 입력해주세요.';
        document.getElementById("check1").value = 'N';
    }
};

function pwcheck() {
    console.log('함수 실행');
    let p1 = document.getElementById('pw1').value;
     let p2 = document.getElementById('pw2').value;
    if(p1 != '' && p2 != '') {
        if( p1 != p2 ) {
            document.getElementById('printmsg2').innerHTML = '비밀번호가 일치하지 않습니다. 다시 확인해주세요.';
            document.getElementById("check2").value = 'N';
        } else {
            document.getElementById('printmsg2').innerHTML = '비밀번호가 일치합니다';
            document.getElementById("check2").value = 'Y';
          }
     } else {
    document.getElementById('printmsg2').innerHTML = '비밀번호를 입력해주세요.';
    document.getElementById("check2").value = 'N';
    }
}

function emailcheck(){
    console.log('함수 실행');
    let email = $('input#emailinfo').val();
    console.log(email);
    if(email != ''){
        $.ajax({
            url:'/emailCheck', 
            type:'POST', 
            data: {email: email},
            success:function(cnt){ 
                if(cnt == 0){ 
                    document.getElementById('printmsg3').innerHTML = '사용 가능한 이메일 입니다.';
                    document.getElementById("check3").value = 'Y';
                } else { 
                    document.getElementById('printmsg3').innerHTML = '이미 사용중인 이메일 입니다.';
                    document.getElementById("check3").value = 'N';
                }
            },
            error:function(){
                document.getElementById('printmsg3').innerHTML = '오류가 발생했습니다.'
                comsole.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }
        });
    } else {
        document.getElementById('printmsg3').innerHTML = '이메일을 입력해주세요.';
        document.getElementById("check3").value = 'N';
    }
};

function datainfo(){
    console.log('함수 실행');
    let form = $("#contactform");
   
    if(document.getElementById("check1").value == 'Y'){
        if(document.getElementById("check2").value == 'Y'){
            if(document.getElementById("check3").value == 'Y'){
                document.getElementById('printmsg4').innerHTML = '';
                form.submit();
            } else {
                document.getElementById('printmsg4').innerHTML = '이메일 중복 체크를 실행해주세요.';
            }
        } else {
            document.getElementById('printmsg4').innerHTML = '비밀번호 확인을 실행해주세요.';
        }
    } else {
        document.getElementById('printmsg4').innerHTML = '아이디 중복 확인을 실행해주세요.';
    }
};

//레이어 팝업
function showlayer(){
    document.getElementById('layer_bg').style = 'display:block';
    $("#layer_bg").css({
        "top": (($(window).height()-$("#layer_bg").outerHeight())/2+$(window).scrollTop())+"px",
        "left": (($(window).width()-$("#layer_bg").outerWidth())/2+$(window).scrollLeft())+"px"
    });

    $("body").css("overflow","hidden");//body 스크롤바 없애기
    
}

$(document).mouseup(function (e){
    var LayerPopup = $("#layer_bg");
    if(LayerPopup.has(e.target).length === 0){
        document.getElementById('layer_bg').style = 'display:none';
        $("body").css("overflow","scroll");
    }
});

//카운트다운 타이머
$(document).ready(function () {
    var myDate = new Date('2022/07/22 23:59:59');
    myDate.setDate(myDate.getDate());

    $("#countdown").countdown(myDate, function (event) {
        $(this).html(
            event.strftime(
                '<div class="timer-wrapper"><div class="time">%D</div><span class="text">days</span></div><div class="timer-wrapper"><div class="time">%H</div><span class="text">hrs</span></div><div class="timer-wrapper"><div class="time">%M</div><span class="text">mins</span></div><div class="timer-wrapper"><div class="time">%S</div><span class="text">sec</span></div>'
            )
        );
    });

});