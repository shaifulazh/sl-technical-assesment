package com.example.demo.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "tasklists")
public class TaskList {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "completed")
	private boolean completed;

    public TaskList () {

    }
    public TaskList (String title, String description, boolean completed) {
		this.title = title;
		this.description = description;
		this.completed = completed;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean isCompleted) {
		this.completed = isCompleted;
	}
    @Override
	public String toString() {
		return "TaskList [id=" + id + ", title=" + title + ", desc=" + description + ", completed=" + completed + "]";
	}
}
