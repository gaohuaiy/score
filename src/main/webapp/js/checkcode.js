/**
 * 验证码
 */
var code;

function createCode() {
	code = "";
	var codeLength = 4; //验证码的长度
	var checkCode = document.getElementById("checkCode");
	var codeChars = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //所有候选组成验证码的字符，当然也可以用中文的
	for(var i = 0; i < codeLength; i++) {
		var charNum = Math.floor(Math.random() * 52);
		code += codeChars[charNum];
	}
	if(checkCode) {
		checkCode.className = "passcode";
		var num1 = code.substring(0,1);
		var num2 = code.substring(1,2);
		var num3 = code.substring(2,3);
		var num4 = code.substring(3,4);
        var inhtmlColor = "<span style='color:red;'>"+num1+"</span>"+"<span style='color:green;'>"+num2+"</span>"+"<span style='color:blue;'>"+num3+"</span>"+"<span style='color:orange;'>"+num4+"</span>";
		checkCode.innerHTML = inhtmlColor;
	}
}

function validateCode() {
	var inputCode = document.getElementById("inputCode").value;
	if(inputCode.length <= 0) {
		alert("请输入验证码！");
		// 添加
		return false;
	} else if(inputCode.toUpperCase() != code.toUpperCase()) {
		alert("验证码输入有误！");
		createCode();
		return false;
	} else {
		inputCode.required = false;
		/*alert("验证码正确！");*/
		return true;
	}
}