package ma.enset.digitalbanking;

import ma.enset.digitalbanking.entities.*;
import ma.enset.digitalbanking.enums.AccountStatus;
import ma.enset.digitalbanking.enums.OperationType;
import ma.enset.digitalbanking.exceptions.BalanceNotSufficientException;
import ma.enset.digitalbanking.exceptions.BankAccountNotFoundException;
import ma.enset.digitalbanking.exceptions.CustomerNotFoundException;
import ma.enset.digitalbanking.repositories.AccountOperationRepository;
import ma.enset.digitalbanking.repositories.BankAccountRepository;
import ma.enset.digitalbanking.repositories.CustomerRepository;
import ma.enset.digitalbanking.services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalBankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            // Initialize sample customers with realistic data
            initializeCustomers(bankAccountService);
            // Create accounts for each customer
            createAccountsAndTransactions(bankAccountService);
        };
    }
    
    private void initializeCustomers(BankAccountService bankAccountService) {
        System.out.println("Initializing customers...");
        Stream.of(
            new String[]{"Mohammed El Alaoui", "mohammed.alaoui@gmail.com"},
            new String[]{"Zakaria Naji", "zakaria.naji@gmail.com"},
            new String[]{"Yasser Benhima", "yasser.benhima@gmail.com"},
            new String[]{"Ilyas Tahiri", "ilyas.tahiri@gmail.com"}
        ).forEach(data -> {
            Customer customer = new Customer();
            customer.setName(data[0]);
            customer.setEmail(data[1]);
            bankAccountService.saveCustomer(customer);
            System.out.println("Created customer: " + data[0]);
        });
    }
    
    private void createAccountsAndTransactions(BankAccountService bankAccountService) {
        System.out.println("Creating accounts and generating transactions...");
        bankAccountService.listCustomers().forEach(customer -> {
            try {
                // Create a current account with 50,000 initial balance and 10,000 overdraft
                bankAccountService.saveCurrentBankAccount(50000, 10000, customer.getId());
                System.out.println("Created current account for: " + customer.getName());
                
                // Create a saving account with 25,000 initial balance and 4.5% interest rate
                bankAccountService.saveSavingBankAccount(25000, 4.5, customer.getId());
                System.out.println("Created saving account for: " + customer.getName());
                
                // Generate sample transactions
                generateSampleTransactions(bankAccountService);
                
            } catch (CustomerNotFoundException e) {
                System.err.println("Error: Customer not found - " + e.getMessage());
            } catch (BankAccountNotFoundException | BalanceNotSufficientException e) {
                System.err.println("Error in transaction processing: " + e.getMessage());
            }
        });
    }
    
    private void generateSampleTransactions(BankAccountService bankAccountService) throws BankAccountNotFoundException, BalanceNotSufficientException {
        List<BankAccountDTO> bankAccounts = bankAccountService.bankAccountList();
        
        // Generate realistic transactions for each account
        for (BankAccountDTO bankAccountDTO : bankAccounts) {
            String accountId = bankAccountDTO.getId();
            
            // Deposit transactions
            bankAccountService.credit(accountId, 5000, "Salary deposit");
            bankAccountService.credit(accountId, 2500, "Freelance payment");
            bankAccountService.credit(accountId, 1000, "Tax refund");
            
            // Withdrawal transactions
            bankAccountService.debit(accountId, 1200, "Rent payment");
            bankAccountService.debit(accountId, 500, "Grocery shopping");
            bankAccountService.debit(accountId, 300, "Utility bills");
            
            // Create a few random transactions
            for (int i = 0; i < 5; i++) {
                double amount = 100 + Math.random() * 900; // Random amount between 100 and 1000
                bankAccountService.debit(accountId, amount, "Shopping expense #" + (i+1));
            }
        }
        
        // Create some transfers between accounts if we have at least 2 accounts
        if (bankAccounts.size() >= 2) {
            bankAccountService.transfer(
                bankAccounts.get(0).getId(), 
                bankAccounts.get(1).getId(), 
                1000.0
            );
        }
    }

    // Alternative implementation using direct repositories (commented out)
    // @Bean
    CommandLineRunner directRepositoryRunner(
            CustomerRepository customerRepository,
            BankAccountRepository bankAccountRepository,
            AccountOperationRepository accountOperationRepository) {
        return args -> {
            // Create customers
            Stream.of("Mohammed", "Zakaria", "Yasser", "Ilyas").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name.toLowerCase() + "@gmail.com");
                customerRepository.save(customer);
            });
            
            // Create accounts for each customer
            customerRepository.findAll().forEach(customer -> {
                // Create current account
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setCustomer(customer);
                currentAccount.setBalance(Math.random() * 90000);
                currentAccount.setCreateDate(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                // Create saving account
                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setCustomer(customer);
                savingAccount.setBalance(Math.random() * 90000);
                savingAccount.setCreateDate(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });

            // Generate operations for each account
            bankAccountRepository.findAll().forEach(acc -> {
                for (int i = 0; i < 10; i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random() * 12000);
                    accountOperation.setType(Math.random() > 0.5 ? OperationType.DEBIT : OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }
            });
        };
    }
}