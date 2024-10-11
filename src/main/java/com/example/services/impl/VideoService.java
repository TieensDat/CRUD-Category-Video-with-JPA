package com.example.services.impl;

import java.util.List;
import com.example.entity.Video;
import com.example.services.IVideoService;

import com.example.dao.*;
import com.example.dao.Impl.*;
public class VideoService implements IVideoService {
	
	IVideoDao videoDao = new VideoDao();

	@Override
	public int count() {
		return videoDao.count();
	}

	@Override
	public List<Video> findAll(int page, int pagesize) {
		return videoDao.findAll();
	}

	@Override
	public List<Video> findByVideoname(String vidname) {
		return videoDao.findByVideoname(vidname);
	}

	@Override
	public List<Video> findAll() {
		return videoDao.findAll();
	}

	@Override
	public Video findById(String videoId) {
		return videoDao.findById(videoId);
	}

	@Override
	public void delete(String videoId) throws Exception {
		 videoDao.delete(videoId);
	}

	@Override
	public void update(Video video) {
		videoDao.update(video);
	}

	@Override
	public void insert(Video video) {
		videoDao.insert(video);
	}

	
}
