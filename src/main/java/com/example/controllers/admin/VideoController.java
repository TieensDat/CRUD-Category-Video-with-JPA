package com.example.controllers.admin;

import java.io.File;
import com.example.entity.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.example.entity.Category;
import com.example.services.ICategoryService;
import com.example.services.impl.CategoryService;
import com.example.services.impl.CategoryService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import static com.example.utils.Constant.*;
import com.example.services.*;
import com.example.services.impl.*;
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
		maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 *5)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add" ,
		"/admin/video/insert" ,"/admin/video/edit" ,"/admin/video/update",
		"/admin/video/delete","/admin/video/search"})
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	public ICategoryService cateService = new CategoryService();
	public IVideoService videoService = new VideoService();
	public ICategoryService cateService = new CategoryService();


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("videos")) {
			IVideoService videoService = new VideoService();
			List<Video> list = videoService.findAll();
			req.setAttribute("videos", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		}else if(url.contains("add")){
			List<Category> list= cateService.findAll();
			req.setAttribute("categories", list);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);

		}else if(url.contains("edit")){
			String videoName = req.getParameter("name");
			List<Video> videos = videoService.findByVideoname(videoName);
			List<Category> list= cateService.findAll();
			req.setAttribute("categories", list);
			req.setAttribute("vid", videos.get(0));
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
			
		}else if(url.contains("delete")){
			String videoId = req.getParameter("name");
			try {
				videoService.delete(videoId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
				

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if (url.contains("insert")) {
			// categoryname ?
			String videoId = req.getParameter("videoid");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int views = Integer.parseInt(req.getParameter("views"));
			int active = Integer.parseInt(req.getParameter("active"));
			int cateID = Integer.parseInt(req.getParameter("categoryId"));
			Video video = new Video();
			video.setVideoid(videoId);
			video.setTitle(title);
			video.setDescription(description);
			video.setViews(views);
			video.setActive(active);
			Category cate = cateService.findById(cateID);
			video.setCategory(cate);
			String fname ="";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					video.setImages(fname);
				}else {
					video.setImages("avatar.png");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}else if(url.contains("update")) {
//			int categoryid = Integer.parseInt(req.getParameter("categoryid"));
//			String categoryname = req.getParameter("categoryname");
//			String status = req.getParameter("status");
//			int statuss = Integer.parseInt(status);	
			String videoId = req.getParameter("videoid");
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int views = Integer.parseInt(req.getParameter("views"));
			int active = Integer.parseInt(req.getParameter("active"));
			int cateID =  Integer.parseInt(req.getParameter("categoryId"));
			Video video = new Video();
			video.setVideoid(videoId);
			video.setDescription(description);
			video.setTitle(title);
			video.setViews(views);
			video.setActive(active);
			Category cate = cateService.findById(cateID);
			video.setCategory(cate);
			// luu hinh anh cu
//			Category cateold = cateService.findById(categoryid);
			List<Video> vidold = videoService.findByVideoname(videoId);
			String fileold = vidold.get(0).getImages();
			// Xu ly images
			String fname ="";
			String uploadPath = UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if(!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("images");
				if(part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// đổi tên file
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index+1);
					fname = System.currentTimeMillis() + "." + ext;
					// upload file
					part.write(uploadPath + "/" + fname);
					// ghi ten file vao data
					video.setImages(fname);
				}else {
					video.setImages(fileold);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");			
		}
	}

}
