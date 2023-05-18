package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.RequestDto.BookRequestDto;
import com.example.Library.Management.System.ResponseDto.BookResponseDto;
import com.example.Library.Management.System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public BookResponseDto addBook(@RequestBody BookRequestDto bookRequestDto){
        return bookService.addBook(bookRequestDto);
    }
}
