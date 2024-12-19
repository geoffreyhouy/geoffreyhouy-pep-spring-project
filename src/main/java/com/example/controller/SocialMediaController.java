package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    /* 1. Register */
    @PostMapping("/register")
    public ResponseEntity<Account> register(Account account) {

    }

    /* 2. Login */
    @PostMapping("/login")
    public ResponseEntity<Account> login(Account account) {

    }

    /* 3. Create new message */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(Message message) {

    }

    /* 4. Get all messages */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        
    }

    /* 5. Get a message by message ID */
    @GetMapping("/messages/{message_id}")
    public ResponseEntity<Message> getMessageByMessageId(@PathVariable int messageId) {

    }

    /* 6. Delete a message by message ID */
    @DeleteMapping("/messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageByMessageId(@PathVariable int messageId) {

    }

    /* 7. Update a message by message ID */
    @PatchMapping("/messages/{message_id}")
    public ResponseEntity<Integer> updateMessageByMessageId(@PathVariable int messageId) {

    }

    /* 8. Get all messages by account ID */
    @GetMapping("/accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId(@PathVariable int accountId) {

    }
}
