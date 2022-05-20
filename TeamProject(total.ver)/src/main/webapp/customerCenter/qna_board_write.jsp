<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&A 게시판</title>
<style type="text/css">
	table.table2{
                border-collapse: separate;
                border-spacing: 1px;
                text-align: left;
                line-height: 1.5;
                border-top: 1px solid #ccc;
                margin : 20px 10px;
        }
        table.table2 tr {
                 width: 50px;
                 padding: 10px;
                font-weight: bold;
                vertical-align: top;
                border-bottom: 1px solid #ccc;
        }
        table.table2 td {
                 width: 100px;
                 padding: 10px;
                 vertical-align: top;
                 border-bottom: 1px solid #ccc;
        }
</style>
</head>
<body>
	<!-- 게시판 등록 -->
	<section id="writeForm">
		<form action="./QNAWritePro.cu" name="QNAWriteForm" method="post">
		<table style="padding-top:50px" align = center width=700 border=0 cellpadding=2 >
                <tr>
                	<td height=20 align= center bgcolor=#ccc><font color=white>Q&A</font></td>
                </tr>
                <tr>
                <td bgcolor=white>
                <table class ="table2">
                        <tr>
                        	<td>글쓴이</td>                        	
                        	<td><input type = text name = qna_mem_id size=20></td>
                        </tr>
                        <tr>
	                        <td>제목</td>
    	                    <td><input type = text name = qna_title size=60></td>
                        </tr>
                        <tr>
	                        <td>내용</td>
    	                    <td><textarea name = qna_content cols=85 rows=15></textarea></td>
                        </tr>
                        <tr>
	                        <td>비밀번호</td>
    	                    <td><input type = password name = qna_pass size=10 maxlength=10></td>
                        </tr>
				</table>
                        <center>
	                        <input type = "submit" value="작성">&nbsp;&nbsp;
    	                    <input type="reset" value="다시쓰기">&nbsp;&nbsp;
        	                <input type="button" value="취소" onclick="history.back()">
                        </center>
        </table>
		</form>
	</section>
</body>
</html>