package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entity.Book;
import com.example.Library.Management.System.Entity.LibraryCard;
import com.example.Library.Management.System.Entity.Transaction;
import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Enum.TransactionStatus;
import com.example.Library.Management.System.Repository.BookRepository;
import com.example.Library.Management.System.Repository.CardRepository;
import com.example.Library.Management.System.Repository.TransactionRepository;
import com.example.Library.Management.System.RequestDto.IssueBookRequestDto;
import com.example.Library.Management.System.ResponseDto.IssueBookResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionService {
    @Autowired
    CardRepository cardRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TransactionRepository transactionRepository;
    public IssueBookResponseDto issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIsIssuedOperation(true);

        LibraryCard libraryCard;
        try {
            libraryCard = cardRepository.findById(issueBookRequestDto.getCardId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Card not found");
            transactionRepository.save(transaction);
            throw new Exception("Card not present");
        }
        Book book;
        try {
            book = bookRepository.findById(issueBookRequestDto.getBookId()).get();
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Book not found");
            transactionRepository.save(transaction);
            throw new Exception("Book not found");
        }
        transaction.setBook(book);
        transaction.setCard(libraryCard);
        if(libraryCard.getStatus() !=  CardStatus.ACTIVATED){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Your card is not activated");
            transactionRepository.save(transaction);
            throw new Exception("Your card is not activated");
        }
        if(book.isIssued() == true){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Sorry book is already issue");
            transactionRepository.save(transaction);
            throw new Exception("Sorry book is already issue");
        }
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("Transaction was successful");
        book.setIssued(true);
        book.setCard(libraryCard);
        book.getTransaction().add(transaction);
        libraryCard.getTransaction().add(transaction);
        libraryCard.getBookIssued().add(book);

        cardRepository.save(libraryCard);

        IssueBookResponseDto issueBookResponseDto = new IssueBookResponseDto();
        issueBookResponseDto.setTransactionNumber(transaction.getTransactionNumber());
        issueBookResponseDto.setTransactionStatus(TransactionStatus.SUCCESS);
        issueBookResponseDto.setBookName(book.getTitle());

        return issueBookResponseDto;

    }
}
