package com.example.online_bank_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.online_bank_system.dto.TransferRequest;
import com.example.online_bank_system.model.Account;
import com.example.online_bank_system.model.Transactions;

@Service
public class TransferService {
    
    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;


    @Transactional
    public void transfer ( TransferRequest data ) {
        
        int senderAccountNo = data.getSenderAccountNo();
        int receiverAccountNo = data.getReceiverAccountNo();

        Account sender = accountService.findByAccountNo(data.getSenderAccountNo());
        Account receiver = accountService.findByAccountNo(data.getReceiverAccountNo());
        
        if (sender == null ) {
            throw new RuntimeException("Sender account not found");
        }

        if (receiver == null ) {
            throw new RuntimeException("Receiver account not found");
        }

        if (sender.getBalance() < data.getAmount() ) {
            throw new RuntimeException("Insufficicent Balance");
        }

        if (!sender.isStatus() ) {
            throw new RuntimeException("Sender account is inactive");
        }

        if ( !receiver.isStatus() ) {
            throw new RuntimeException("Receiver account is inactive");
        }

        if ( sender.getAccountNo() == receiver.getAccountNo()) {
            throw new RuntimeException("Cannot transfer to same account");
        }

        String senderName = sender.getUser().getName();
        String receiverName = receiver.getUser().getName();

        double amount = data.getAmount();

        //Debit from sender
        sender.setBalance( sender.getBalance() - amount );

        //Credit Receiver
        receiver.setBalance(receiver.getBalance() + amount);

        accountService.save(sender);
        accountService.save(receiver);

        Transactions senderTxn = new Transactions();
        senderTxn.setAccountNo(senderAccountNo);
        senderTxn.setTxnType("SEND");
        senderTxn.setAmount(amount);
        senderTxn.setRelatedAccountNumber(receiverAccountNo);
        senderTxn.setDescription("Sent to "+ receiverName);
        senderTxn.setStatus(true);

        transactionService.saveTransactions(senderTxn);

        Transactions receiverTxn = new Transactions();
        receiverTxn.setAccountNo(receiverAccountNo);
        receiverTxn.setTxnType("RECEIVE");
        receiverTxn.setAmount(amount);
        receiverTxn.setRelatedAccountNumber(senderAccountNo);
        receiverTxn.setDescription("Received from  "+ senderName);
        receiverTxn.setStatus(true);

        transactionService.saveTransactions(receiverTxn);

        

    }
}
