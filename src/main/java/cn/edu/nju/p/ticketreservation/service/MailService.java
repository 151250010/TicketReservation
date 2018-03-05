package cn.edu.nju.p.ticketreservation.service;

public interface MailService {

    void sendSimpleMail(String to, String subject, String content);
}
