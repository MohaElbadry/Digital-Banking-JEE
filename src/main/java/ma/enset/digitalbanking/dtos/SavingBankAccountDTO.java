package ma.enset.digitalbanking.dtos;


import lombok.Data;
import ma.enset.digitalbanking.enums.AccountStatus;

import java.util.Date;


@Data
public class SavingBankAccountDTO extends BankAccountDTO {
    private String id;
    private double balance;
    private Date createDate;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;
}
