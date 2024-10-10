<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
    
<html>
<head>
    <title>Add New Video</title>
</head>
<body>
    <h2>Add New Video</h2>
    <form action="${pageContext.request.contextPath }/admin/video/insert" method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>Video ID:</td>
                <td><input type="text" name="videoid" value="${vid.videoid}" hidden="hidden" /></td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><input type="text" name="title" value="${vid.title}" required /></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><textarea name="description" value="${vid.description}" rows="4" cols="50"></textarea></td>
            </tr>
            <tr>
               <c:choose>
					<c:when test="${vid.images != null && vid.images.length() >= 5 && vid.images.substring(0,5) != 'https'}">
						<c:url value="/image?fname=${vid.images}" var="imgUrl"></c:url>
					</c:when>
					<c:otherwise>
						<c:url value="${vid.images}" var="imgUrl"></c:url>
					</c:otherwise>
				</c:choose>
				<img id="imagess" style="max-height: 150px; max-width: 200px;" src="${imgUrl}" />	
            </tr>
            <tr>
                <td>Views:</td>
                <td><input type="text" name="views" value="${vid.views}" /></td>
            </tr>
            <tr>
                <td>Status (Active = 1, Inactive = 0):</td>
                <td><input type="text" name="active" value="${vid.active}" /></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select name="categoryId" required>
                        <option value="">Select a Category</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryId}">${category.categoryname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="Add Video" /></td>
            </tr>
        </table>
    </form>
</body>
</html>
