package com.example.entity;

import java.io.Serializable;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@NamedQuery(name="Category.findAll" , query="SELECT c FROM Category c")
@NoArgsConstructor
@Getter
@Setter
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CategoryId")
	private int categoryId;
	
	@Column(name="Categoryname", columnDefinition="NVARCHAR(200) NOT NULL")
	private String categoryname;
	
	@Column(name="Images", columnDefinition="NVARCHAR(500) NULL")
	private String images;
	
	@Column(name="Status")
	private int status;
	
	@OneToMany(mappedBy="category")
	private List<Video> videos;

}
