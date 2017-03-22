package com.manulsoftware.weddings.service;


import com.manulsoftware.weddings.entity.Email;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailService {
    void sendEmail(final Email email) throws MessagingException;
}
