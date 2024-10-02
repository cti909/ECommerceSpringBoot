package com.example.DemoSpringBootAPI.Repository.Implements;

import jakarta.persistence.EntityManager;

public class PostCustomRepository 
{
	private final EntityManager entityManager;
    
    public PostCustomRepository(EntityManager entityManager) 
    {
    	this.entityManager = entityManager;
    }
}
