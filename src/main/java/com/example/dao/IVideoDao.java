package com.example.dao;

import java.util.List;

import com.example.entity.Video;

public interface IVideoDao {

	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String vidname);
	

	List<Video> findAll();

	Video findById(String videoId);

	void delete(String videoid) throws Exception;

	void update(Video video);

	void insert(Video video);

}
