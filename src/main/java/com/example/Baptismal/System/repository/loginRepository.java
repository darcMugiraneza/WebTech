package com.example.Baptismal.System.repository;

import com.example.Baptismal.System.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface loginRepository extends JpaRepository<Users, Long> {
    Users findByName (String name);
}
