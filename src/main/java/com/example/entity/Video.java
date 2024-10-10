package com.example.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll" , query="SELECT v FROM Video v")
@NoArgsConstructor
@Getter
@Setter
public class Video  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="VideoId")
	private String videoid;
	
	@Column(name="Active")
	private int active;
	
	@Column(name="Description", columnDefinition = "NVARCHAR(500)")
	private String description;
	
	@Column(name="Images")
	private String images;
	
	@Column(name="Title", columnDefinition = "NVARCHAR(500)")
	private String title;
	
	@Column(name="Views")
	private int views;
	
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;

	
	
}
