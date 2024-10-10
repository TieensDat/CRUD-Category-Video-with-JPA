package com.example.services;

import java.util.List;

import com.example.entity.Video;

public interface IVideoService {
	int count();

	List<Video> findAll(int page, int pagesize);

	List<Video> findByVideoname(String vidname);

	List<Video> findAll();

	Video findById(int videoId);

	void delete(int videoId) throws Exception;

	void update(Video video);

	void insert(Video video);
}
