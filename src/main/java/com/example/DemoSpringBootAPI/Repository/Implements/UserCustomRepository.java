package com.example.DemoSpringBootAPI.Repository.Implements;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.DemoSpringBootAPI.Repository.Interfaces.IUserCustomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class UserCustomRepository implements IUserCustomRepository
{
    private final EntityManager entityManager;
    
    public UserCustomRepository(EntityManager entityManager) 
    {
    	this.entityManager = entityManager;
    }

//	@Override
//	public List<Post> findPostsByCustomCriteria(String customParam) 
//	{
//        String sql = "SELECT p FROM Post p WHERE p.title LIKE :customParam";
//        Query query = entityManager.createQuery(sql);
//        query.setParameter("customParam", "%" + customParam + "%");
//        return query.getResultList();
//    }

}
