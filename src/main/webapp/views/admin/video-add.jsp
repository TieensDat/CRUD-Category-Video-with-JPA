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
                <td><input type="text" name="videoid" required /></td>
            </tr>
            <tr>
                <td>Title:</td>
                <td><input type="text" name="title" required /></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><textarea name="description" rows="4" cols="50"></textarea></td>
            </tr>
            <tr>
                <td>Images URL:</td>
                <td><input type="file" name="images" /></td>
            </tr>
            <tr>
                <td>Views:</td>
                <td><input type="text" name="views" value="0" /></td>
            </tr>
            <tr>
                <td>Status (Active = 1, Inactive = 0):</td>
                <td><input type="text" name="active" value="1" /></td>
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
