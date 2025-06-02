package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {

 

}
