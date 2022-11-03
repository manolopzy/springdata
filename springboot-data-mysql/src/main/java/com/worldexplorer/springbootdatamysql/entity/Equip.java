package com.worldexplorer.springbootdatamysql.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Data is a Lombok annotation, which generates a default constructor (if you
 *       don’t have one) and all the setters, getters, and overrides, such as
 *       the toString method, to make our class cleaner.
 * @author tanku
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Equip {

	@Id
	private int id;
	private String description;

	@Column(insertable = true, updatable = false)
	private LocalDateTime created;
	private LocalDateTime modified;
	private boolean completed;
	

	@PrePersist
	void onCreate() {
		this.setCreated(LocalDateTime.now());
		this.setModified(LocalDateTime.now());
	}

	@PreUpdate
	void onUpdate() {
		this.setModified(LocalDateTime.now());
	}

	
}
