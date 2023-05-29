package com.group6.project.relational.account;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.junit.jupiter.api.DisplayName;

import java.sql.Date;


//@DataJpaTest
//@ActiveProfiles("test")
@SpringBootTest
public class AccountTest {

    @DisplayName("Test Create Account")
    @Test
    public void testAccount(){
        Account account = new Account();
        account.setID(23);
        account.setUserName("Larry");
        account.setPassword("David");

        String expectedNoError = "Account(ID=23, userName=Larry, password=David, facebookID=null, appleID=null, googleID=null, ipAddress=null, country=null, state=null, city=null, signupDate=null, lastSeenDate=null, currencies=null, inventory=null, roles=null)";
        assertEquals(expectedNoError, account.toString());
    }

    @Autowired
    private AccountRepository accountRepository;

    @DisplayName("Test Adding Account")
    @Test
    public void testAddingAccountComposition() {
        Account account = new Account();
        account.setID(27);
        account.setUserName("Larry");
        account.setPassword("David");
        account.setSignupDate(new Date(1681664605149L));
        account.setLastSeenDate(new Date(1681664605149L));

        var b4Add = accountRepository.count();
        accountRepository.save(account);
        var afterAdd = accountRepository.count();

        assertEquals(b4Add + 1, afterAdd);

        var foundAccount = accountRepository.findByUserName("Larry");
        assertEquals("Larry", foundAccount.get().getUserName());
    }
}