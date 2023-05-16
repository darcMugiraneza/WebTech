package com.example.Baptismal.System.repository;

import com.example.Baptismal.System.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Users,Long>{

    List<Users> findByName(String name);
}