package com.acme.test01.yournameandsurname.controller;

import com.acme.test01.yournameandsurname.model.ChequeDto;
import com.acme.test01.yournameandsurname.model.SavingsDto;
import com.acme.test01.yournameandsurname.service.AccountImplementationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/AccountController")
public class AccountController
{
    @Autowired
    private AccountImplementationService accountImplementationService;

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus fetchAllAccounts(){
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/createSavingsAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createSavingsAccount(@RequestBody SavingsDto request) throws Exception {
        log.info("AccountController.createSavingsAccount");
        log.info("Savings Details Dto: " + request);
        if (request != null) {
            accountImplementationService.openSavingsAccount(request.getAccountId(),request.getAmountToDeposit());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/createChequeAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createChequeAccount(@RequestBody ChequeDto request) throws Exception {
        log.info("AccountController.createChequeAccount");
        log.info("ChequeDto Details Dto: " + request);
        if (request != null) {
            accountImplementationService.openCurrentAccount(request.getAccountId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/withdrawAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> withdrawAccount(@RequestBody ChequeDto request) throws Exception {
        log.info("AccountController.withdrawAccount");
        log.info("ChequeDto Details Dto: " + request);
        if (request != null) {
            accountImplementationService.withdraw(request.getAccountId(),request.getAmountToWithdraw());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/depositIntoAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> depositIntoAccount(@RequestBody ChequeDto request) throws Exception {
        log.info("AccountController.depositIntoAccount");
        log.info("ChequeDto Details Dto: " + request);
        if (request != null) {
            accountImplementationService.deposit(request.getAccountId(),request.getAmountToDeposit());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
