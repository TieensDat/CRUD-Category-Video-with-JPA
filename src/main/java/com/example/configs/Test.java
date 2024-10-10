package com.example.configs;
import com.example.entity.Category;
import com.example.entity.Video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class Test {
	public static void main(String[] args) {
		 EntityManager enma = JPAConfig.getEntityManager();
		 EntityTransaction trans = enma.getTransaction();
		 Video video = new Video();
		 try {
			 trans.begin();
			 trans.commit();
		 } catch (Exception e){
			 e.printStackTrace();
			 trans.rollback();
			 throw e;
		 }finally {
			 enma.close();
		 }
	}
}
