<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="jakarta.tags.core"%>



<a href="${pageContext.request.contextPath }/admin/video/add">Add
	Category</a>
<table border="1" width="100%">
	<tr>
		<th>STT</th>
		<th>Video ID</th>
		<th>Images</th>
		<th>Video Title</th>
		<th>Description</th>
		<th>View</th>
		<th>Category</th>
		<th>Status</th>
		<th>Action</th>

	</tr>

	<c:forEach items="${videos}" var="video" varStatus="STT">
		<tr>
			<td>${STT.index + 1}</td>
			<td>${video.videoid}</td>
			<td><c:choose>
					<c:when
						test="${video.images != null && video.images.length() >= 5 && video.images.substring(0,5) != 'https'}">
						<c:url value="/image?fname=${video.images}" var="imgUrl"></c:url>
					</c:when>
					<c:otherwise>
						<c:url value="${video.images}" var="imgUrl"></c:url>
					</c:otherwise>
				</c:choose> <img style="max-height: 150px; max-width: 200px;" src="${imgUrl}" />
			</td>
			<td>${video.title}</td>
			<td>${video.description}</td>
			<td>${video.views}</td>
			<td>${video.category.categoryname}</td>
			<td>
				<%-- <c:choose>
					<c:when test="${cate.status == true}">
						Active
					</c:when>
					<c:otherwise>
						Inactive
					</c:otherwise>
				</c:choose> --%> 
				<c:if test="${video.active == 1 }">
					<span>Hoạt động</span>
				</c:if> <c:if test="${video.active != 1 }">
					<span>Khoá</span>
				</c:if>
			</td>
			<td>${video.active}</td>
			<!-- Action  -->
			<td><a
				href="<c:url value='/admin/video/edit?name=${video.videoid}'/>">Sửa</a>
				| <a
				href="<c:url value='/admin/video/delete?name=${video.videoid}'/>">Xóa</a>
			</td>

			


		</tr>
	</c:forEach>
</table>
