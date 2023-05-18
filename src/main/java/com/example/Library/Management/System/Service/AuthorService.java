package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entity.Author;
import com.example.Library.Management.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public void addAuthor(Author author){
        authorRepository.save(author);
    }
    public List<Author> getAuthor(){
        return authorRepository.findAll();
    }

}
