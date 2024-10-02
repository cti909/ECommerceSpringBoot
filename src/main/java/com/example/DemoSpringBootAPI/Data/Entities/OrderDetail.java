package com.example.DemoSpringBootAPI.Data.Entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.example.DemoSpringBootAPI.Data.EntityEnum.ProductSize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "orderDetails")
public class OrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
    private Integer quantity;
    
	// each time have different price
	@Column(nullable = false)
    private Long price;
	
    @Column(nullable = false)
    private Boolean isDeleted = false;

//    @Column(nullable = false)
//    private LocalDateTime createdAt = LocalDateTime.now();
//
//    @Column(nullable = false)
//    private LocalDateTime updatedAt = LocalDateTime.now();

    // ----------Related-----------

    @ManyToOne
    @JoinColumn(name = "orderId", referencedColumnName = "id", nullable = false)
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "id", nullable = false)
    private Product product;
}
