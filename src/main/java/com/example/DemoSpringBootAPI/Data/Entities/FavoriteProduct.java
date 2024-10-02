package com.example.DemoSpringBootAPI.Data.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "favoriteProducts")
public class FavoriteProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// ----------Related-----------
	@ManyToOne
	@JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
	private User user;
}
