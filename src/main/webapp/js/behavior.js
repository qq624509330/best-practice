function simpleAjax() {

	var jsonObj = {
		'name' : '啊',
		'date' : '2017-01-02'
	};

	$.ajax({
		type : "post",
		url : "/simple_	ajax",
		dataType : "json",
		contentType : 'application/json',
		data : JSON.stringify(jsonObj),
		success : function(data) {
			alert(data);
		},
		error : function(data) {
			alert('error');
		}
	});
}

function uploadFile() {
	$('#file-upload-form').ajaxSubmit({
		type : "post",
		url : '/file_upload',
		dataType : "json",
		success : function(resp) {
			alert("a");
		}
	});
}

function setCookie() {
	var expiresTime = new Date();
	expiresTime.setTime(expiresTime.getTime() + 0.5 * 60 * 1000);
	$.cookie('token', '曲奇饼干', {
		expires : expiresTime,
		path : '/'
	});
}

function errorBind() {
	var jsonObj = {
		'age' : '111',
		'birthday' : '2017-01-02 00:00:21'
	};
	$.ajax({
		type : "post",
		url : "/error_bind",
		dataType : "json",
		contentType : 'application/json',
		data : JSON.stringify(jsonObj),
		success : function(data) {
			alert(data);
		},
		error : function(data) {
			alert('error');
		}
	});
}

function interaction() {
	var jsonObj = {
		'name' : '啊aaaa', // 乱码问题
		'date' : '1905-01-02 14:23:59', // 时间转化问题
		'money' : '10.333', // 小数
		'no' : '9', // 数字
		'serial' : '2147483999' // Long
	};
	$.ajax({
		type : "post",
		url : "/interaction",
		dataType : "json",
		contentType : 'application/json',
		data : JSON.stringify(jsonObj),
		success : function(resp) {
			if (resp.result) {
				alert(resp.data);
				// 或者解析这个resp.data
			} else {
				alert(resp.errmsg);
			}
		},
		error : function(data) {
			// TODO
		}
	});
}