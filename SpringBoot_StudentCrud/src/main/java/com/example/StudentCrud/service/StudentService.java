package com.example.StudentCrud.service;

import com.example.StudentCrud.model.Student;
import com.example.StudentCrud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService  {

    @Autowired
    private StudentRepository repository;

    public List<Student> listAll(){
        return repository.findAll();
    }

    public void save(Student std){
        repository.save(std);
    }

    public Student get(long id){
        return repository.findById(id).orElseThrow();
    }

    public void delete(long id){
        repository.deleteById(id);
    }


}
