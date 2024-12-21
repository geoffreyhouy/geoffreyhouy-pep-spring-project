package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) {
        String messageText = message.getMessageText();
        if (messageText.length() == 0) {
            throw new IllegalArgumentException("Message text must be at least 1 character long");
        }
        if (messageText.length() >= 255) {
            throw new IllegalArgumentException("Message text must be less than 255 characters long");
        }
        int postedBy = message.getPostedBy();
        if (accountRepository.findById(postedBy).isEmpty()) {
            throw new IllegalArgumentException("Invalid account");
        }
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(int messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isEmpty()) {
            return null;
        }
        return optionalMessage.get();
    }

    public int deleteMessageByMessageId(int messageId) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isEmpty()) {
            return 0;
        }
        messageRepository.deleteById(messageId);
        return 1;
    }

    public int updateMessageByMessageId(int messageId, String messageText) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (optionalMessage.isEmpty()) {
            throw new IllegalArgumentException("Invalid message");
        }
        if (messageText.length() == 0) {
            throw new IllegalArgumentException("Message text must be at least 1 character long");
        }
        if (messageText.length() >= 255) {
            throw new IllegalArgumentException("Message text must be less than 255 characters long");
        }
        Message message = optionalMessage.get();
        message.setMessageText(messageText);
        messageRepository.save(message);
        return 1;
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        return messageRepository.findAllByPostedBy(accountId);
    }
}
