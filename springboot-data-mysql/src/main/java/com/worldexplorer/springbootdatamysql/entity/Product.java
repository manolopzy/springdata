package com.worldexplorer.springbootdatamysql.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data//Equivalent to {@code @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode}.
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotEmpty(message = "the product's name must not be empty")
	private String name;
	
	private String description;
	@Positive(message = "the quantity of the product should be positive")
	private double stock;
	
	private float price;
	
	private String status;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAtDate;
	/**
	 * Defines the set of cascadable operations that are propagated 
	 * to the associated entity.
	 * The value <code>cascade=ALL</code> is equivalent to 
	 * <code>cascade={PERSIST, MERGE, REMOVE, REFRESH, DETACH}</code>.
	 */
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)//Defines strategies for fetching data from the database.
	//@JoinColumn(referencedColumnName = "id")//the foreign key The name of the foreign key column.
	/**
	 * Default (only applies if a single join column is used):
     * The concatenation 并聯，并列 of the following: the name of the 
     * referencing relationship property or field of the referencing 
     * entity or embeddable class; "_"; the name of the referenced 
     * primary key column. 
	 */
	@JoinColumn(name = "category_id")
	/**
	 * Annotation that can be used to either suppress 
	 * serialization of properties
	 * (during serialization), or ignore processing 
	 * of JSON properties read (during
	 * deserialization).
	 */
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotNull(message = "la categoria no deberia ser null")
	private Category category;
	
}
