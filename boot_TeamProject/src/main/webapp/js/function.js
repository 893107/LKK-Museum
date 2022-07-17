/* ajax
 - javascript에서 서버와 브라우저가 비동기 통신을 하게 도와주는 기능
 - 웹 페이지를 다시 로딩하지 않고 데이터를 불러올 수 있게 해줌
*/

function idcheck(){
    console.log('함수 실행');
    var id = $('input#idinfo').val();
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
                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
                    document.getElementById('printmsg1').innerHTML = '이미 사용중인 아이디 입니다.';
                }
            },
            error:function(){
                document.getElementById('printmsg1').innerHTML = '오류가 발생했습니다.'
                comsole.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    
            }
        });
    } else {
        document.getElementById('printmsg1').innerHTML = '아이디를 입력해주세요.'
    }
};

function pwcheck() {
    console.log('함수 실행');
    let p1 = document.getElementById('pw1').value;
     let p2 = document.getElementById('pw2').value;
    if(p1 != '' && p2 != '') {
        if( p1 != p2 ) {
            document.getElementById('printmsg2').innerHTML = '비밀번호가 일치하지 않습니다. 다시 확인해주세요.';
           return false;
        } else{
             document.getElementById('printmsg2').innerHTML = '비밀번호가 일치합니다';
             return true;
          }
     } else {
    document.getElementById('printmsg2').innerHTML = '비밀번호를 입력해주세요.'
    }
}

function emailcheck(){
    console.log('함수 실행');
    var email = $('input#emailinfo').val();
    console.log(email);
    if(email != ''){
        $.ajax({
            url:'/emailCheck', 
            type:'POST', 
            data: {email: email},
            success:function(cnt){ 
                if(cnt == 0){ 
                    document.getElementById('printmsg3').innerHTML = '사용 가능한 이메일 입니다.';
                } else { 
                    document.getElementById('printmsg3').innerHTML = '이미 사용중인 이메일 입니다.';
                }
            },
            error:function(){
                document.getElementById('printmsg3').innerHTML = '오류가 발생했습니다.'
                comsole.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    
            }
        });
    } else {
        document.getElementById('printmsg3').innerHTML = '이메일을 입력해주세요.'
    }
};