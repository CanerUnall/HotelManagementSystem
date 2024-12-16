package com.cap.MolvenoLakeResort.service.email;

import java.io.IOException;

public interface EmailService {

    void sendEmail(String to, String subject, String text) throws IOException;
}
