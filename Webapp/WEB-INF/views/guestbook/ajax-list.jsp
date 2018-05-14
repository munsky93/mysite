<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link
	href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/mysite.css"
	rel="stylesheet" type="text/css">
<link
	href="${pageContext.request.contextPath }/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">


<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.min.js"></script>

<title>Insert title here</title>
</head>
<body>

	<div id="container">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>

		<div id="wrapper">
			<div id="content">
				<div id="guestbook">

					<table>
						<tr>
							<td>이름</td>
							<td><input type="text" name="name" /></td>
							<td>비밀번호</td>
							<td><input type="password" name="password" /></td>
						</tr>
						<tr>
							<td colspan=4><textarea name="content" id="content"></textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input id="btnAdd" type="submit"
								VALUE=" 등록 " /></td>
						</tr>
					</table>
					
									 
					
					<ul id="guestbook-list">
						<!-- 방명록리스트 -->

                   </ul>

				</div>
				<!-- /guestbook -->
			</div>
			<!-- /content -->
		</div>
		<!-- /wrapper -->

		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>
		

	</div>
	<!-- /container -->
	

	<!-- 삭제팝업(모달)창 -->
	<div class="modal fade" id="del-pop">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label> <input type="password" name="modalPassword" id="modalPassword"><br> 
					<label>번호</label></label><input type="text"name="modalPassword" value="" id="modalNo"> <br>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button type="button" class="btn btn-danger" id="btn_del">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
	$(document).ready(function() {
		fetchList();
	});
	
	
	$("#btn_del").on("click", function() {
		console.log("btn_del");
		
		var no = $("[id=modalNo]").val();
		var password = $("[id=modalPassword]").val();
		console.log(no);
		console.log(password);
		
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/delete2",
			type : "post",
			data : {
				no : no,
				password : password
			},
			dataType : "json",
			success : function(delno) {
			  if(delno > 0) {
				  $("#"+delno).remove();                 //delno는 삭제하는 번호를 말하고 remove를 통해 삭제한다!
			  } else
				 $("[id=modalPassword]").val("");      //비밀번호에 텍스트창 안에 비번을 빈문자로 만들어준다.
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
	
	})
	
	$("#del-pop").modal("hide");   //삭제버튼을 눌렀을때 방명록화면이 나오게 하는 바
	
	});
	
	$("ul").on("click", "input", function() {
		                                        /* attr는 Attribute를 뜻 */
		var modalNo = $(this).attr("class");    /* 밑에 class안에 있는 숫자(결과값에서 번호)를 this 하나만 선택해서 삭제하기 위해쓴다 */
	 	$("[id=modalNo]").val(modalNo);         /* 결과값에서 번호가 삭제를 눌렀을때 그대로 새로운창에 번호가 똑같이 가도록하기 위한것 */
		$("#del-pop").modal();
	});

	
	$("#btnAdd").on("click", function() {
		console.log("btnAdd");

		var name = $("[name=name]").val();
		var password = $("[name=password]").val();
		var content = $("[name=content]").val();
		console.log(name);
		console.log(password);
		console.log(content);
		
		
		/* 리스트 요청 ajax */
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/add",
			type : "post",
			data : {
				name : name,
				password : password,
				content : content
			},
			dataType : "json",
			success : function(guestbookVo) {
				render(guestbookVo, "up");
				$("[name=name]").val("");
				$("[name=password]").val("");
				$("[name=content]").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
	});

	
	function fetchList() {
		/* 리스트 요청 ajax */
		$.ajax({
			url : "${pageContext.request.contextPath }/api/guestbook/list",
			type : "get",
			dataType : "json",
			success : function(list) {
				/*성공시 처리해야할 코드 작성*/
				console.log(list);
				for (var i = 0; i < list.length; i++) {

					render(list[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})

	}


	function render(guestbookVo, updown) {
		var str = "";
		str += "<li id='"+guestbookVo.no+"'>";
		str += "    <table>";
		str += "        <tr>";
		str += "           <td>[" + guestbookVo.no + "]</td>";                 /* 번호가 하나씩 올라가는것을 나타내기 위한 것 */
		str += "           <td>" + guestbookVo.name + "</td>";
		str += "           <td><input id ='btnModal' type='button' class='" + guestbookVo.no + "' value='삭제'/></a></td>";
		str += "        </tr>";
		str += "        <tr>";
		str += "           <td colspan=4>";
		str += guestbookVo.content ;
		str += "           </td>";
		str += "        </tr>";
		str += "    </table>";
		str += "    <br>";
		str += "</li>"

		if (updown == "up") {
			$("#guestbook-list").prepend(str);
		} else if (updown == "down") {
			$("#guestbook-list").append(str);
		} else {
			console.log("오류");
		}
	}
</script>
</html>
