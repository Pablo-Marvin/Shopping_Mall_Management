package com.sm.im;

import org.springframework.data.jpa.repository.JpaRepository;
// It is parameterized with two types
public interface ItemRepository extends JpaRepository<Item, Integer> 
 {

}
