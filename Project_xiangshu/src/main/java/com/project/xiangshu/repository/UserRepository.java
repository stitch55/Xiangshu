package com.project.xiangshu.repository;

import com.project.xiangshu.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
//Repository 适合Entity 绑定的
public interface UserRepository extends JpaRepository<User,Integer>{

}