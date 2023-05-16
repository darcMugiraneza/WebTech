package com.example.Baptismal.System.service;

import com.example.Baptismal.System.Model.Users;
import com.example.Baptismal.System.repository.MemberRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private MemberRepository mrepo;
    public void saveMember(Users u){
     mrepo.save(u);
    }

    public List<Users> getallUser(){
        return mrepo.findAll();
    }



    public void deleteUserById(Long id){

        mrepo.deleteById(id);

    }


    public Users getUserById(Long id){

        return  mrepo.findById(id).get();
    }


    public List<Users> searchUsers(String keyword) {
        return mrepo.findByName(keyword);
    }
}
