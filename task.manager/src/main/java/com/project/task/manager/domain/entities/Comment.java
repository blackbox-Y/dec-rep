package com.project.task.manager.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table (name = "comment_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode (of = "id")
public class Comment {
	@Id
	@Column
	@SequenceGenerator(
			name = "task_Id_sequence",
			sequenceName = "task_Id_sequence",
			initialValue = 1,
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.AUTO
			)
	private Long id;
	
	
	@ManyToOne
	@JoinColumn(
			name = "commenter_id",
			referencedColumnName = "id"
			)
	private User commenter;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
		@JoinColumn (name = "task_id", referencedColumnName = "id"),
		@JoinColumn (name = "title", referencedColumnName = "title")
		})
	private Task task;
	

	@Column(name = "text")
	private String text;
	
	
	
}
