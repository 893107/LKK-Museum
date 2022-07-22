let index = {
    init: function () {

        $("#finalbtn").on("click", () => {
            this.save(); 
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

    },

    save: function () {
        
    	console.log('함수 실행');
        let form = $("#contactform");
        let email = $('input#email').val();
    	
    	let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            phone: $("#phone").val(),
            name: $("#name").val(),
            budget: $("#budget").val()
        }
        
    	if(document.getElementById("check1").value == 'Y'){
            if(document.getElementById("check2").value == 'Y'){
                if(document.getElementById("check3").value == 'Y'){
                    document.getElementById('printmsg4').innerHTML = '';
                    $.ajax({

                        type: "POST",
                        url: "/auth/joinProc",
                        data: JSON.stringify(data), 
                        contentType: "application/json; charset=utf-8", 
                        dataType: "json" 
                    }).done(function (res){
                        if(res.status === 500) {
                            alert("회원가입에 실패하였습니다.");
                        } else {
                            alert("회원가입이 완료되었습니다.");
                            location.href = "/";
                        }

                    }).fail(function (error) {
                        alert(JSON.stringify(error));
                    });
	                }else {
	                    document.getElementById('printmsg4').innerHTML = '이메일 중복 체크를 실행해주세요.';
	                }
	            } else {
	                document.getElementById('printmsg4').innerHTML = '비밀번호 확인을 실행해주세요.';
	            }
	        } else {
	            document.getElementById('printmsg4').innerHTML = '아이디 중복 확인을 실행해주세요.';
	        }},

	        
	        update: function () {
	            let data = {
	                id: $("#id").val(),
	                username: $("#username").val(),
	                password: $("#password").val(),
	                email: $("#email").val(),
	                phone: $("#phone").val(),
	                name: $("#name").val(),
	                budget: $("#budget").val()
	            }
	            
	            if(document.getElementById("check2").value == 'Y'){
	                  document.getElementById('printmsg4').innerHTML = '';
	            $.ajax({
	                type: "PUT",
	                url: "/user",
	                data: JSON.stringify(data),
	                contentType: "application/json; charset=utf-8",
	                dataType: "json"
	            }).done(function (res) {
	                alert("회원 정보 수정이 완료되었습니다.");
	                location.href = "/";
	            }).fail(function (error) {
	                alert(JSON.stringify(error));
	            });
	                
	            } else {
	                document.getElementById('printmsg4').innerHTML = '비밀번호 확인을 실행해주세요.';
	            }
	        },

	        }

index.init();