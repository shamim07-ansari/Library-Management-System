package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entity.Author;
import com.example.Library.Management.System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
        return "Author Added";
    }
    @GetMapping()
    public List<Author> getAuthor(){
        return authorService.getAuthor();
    }
}
