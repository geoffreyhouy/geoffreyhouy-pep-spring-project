package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.AccountAlreadyExistsException;
import com.example.exception.AuthenticationException;
import com.example.service.AccountService;
import com.example.service.MessageService;

@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /* 1. Register */
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.register(account));
    }

    /* 2. Login */
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.login(account));
    }

    /* 3. Create new message */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.createMessage(message));
    }

    /* 4. Get all messages */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessages());
    }

    /* 5. Get a message by message ID */
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable("message_id") int messageId) {
        Message message = messageService.getMessageByMessageId(messageId);
        if (message == null) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    /* 6. Delete a message by message ID */
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageByMessageId(@PathVariable("message_id") int messageId) {
        int rowsUpdated = messageService.deleteMessageByMessageId(messageId);
        if (rowsUpdated == 0) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(rowsUpdated);
    }

    /* 7. Update a message by message ID */
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessageByMessageId(@PathVariable("message_id") int messageId, @RequestBody Message message) {
        int rowsUpdated = messageService.updateMessageByMessageId(messageId, message.getMessageText());
        if (rowsUpdated == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(rowsUpdated);
    }

    /* 8. Get all messages by account ID */
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable("account_id") int accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessagesByAccountId(accountId));
    }

    /* Handle 400 Bad Request */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequest(Exception e) {
        return e.getMessage();
    }

    /* Handle 401 Unauthorized */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorized(Exception e) {
        return e.getMessage();
    }

    /* Handle 409 Conflict */
    @ExceptionHandler(AccountAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflict(Exception e) {
        return e.getMessage();
    }
}
