package com.example.Library.Management.System.ResponseDto;

import com.example.Library.Management.System.Enum.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueBookResponseDto {

    private String transactionNumber;

    private String bookName;

    private TransactionStatus transactionStatus;
}
