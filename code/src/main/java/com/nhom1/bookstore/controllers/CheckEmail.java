package com.nhom1.bookstore.controllers;

import org.springframework.stereotype.Controller;

import com.nhom1.bookstore.services.AccountService;

@Controller
public class CheckEmail {
    boolean checkEmail(AccountService accountService, String email) {
        if(accountService.checkEmail(email)) {
            return false;
        } else{
            return true;
        }
    }
}
