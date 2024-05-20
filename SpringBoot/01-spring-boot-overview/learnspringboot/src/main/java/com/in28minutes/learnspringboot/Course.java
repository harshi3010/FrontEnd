package com.in28minutes.learnspringboot;

public class Course {
			private int id;
			private String name;
			private String author;
			public Course(int id, String name, String author) {
				super();
				this.id = id;
				this.name = name;
				this.author = author;
			}
			public int getId() {
				return id;
			}
			public String getName() {
				return name;
			}
			public String getAuthor() {
				return author;
			}
			@Override
			public String toString() {
				return "Course [id=" + id + ", name=" + name + ", author=" + author + "]";
			}		
}
