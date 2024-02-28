package com.PTD.accounts.accounts;

import com.PTD.accounts.entity.Accounts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AccountRepository {
    @Autowired
    private com.PTD.accounts.repository.AccountRepository accountRepository;

    @Test
    public void findById(){
        Accounts accounts= accountRepository.findById(1594016850L).get();
        System.out.println("CustomerId ="+accounts.getCustomerId());
    }
}
