package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.AccountNotFoundException;
import com.example.demo.exception.BalanceException;
import com.example.demo.model.Account;
import com.example.demo.model.Txn;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;

@Service
public class TxrServiceImpl implements TxrService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionOneRepository transactionRepository;

	@Transactional
	@Override
	public boolean txr(double amount, String fromAccNum, String toAccNum) {

		Account fromAccount =
				accountRepository.findById(fromAccNum)
				.orElseThrow(()->new AccountNotFoundException(fromAccNum + " not exist"));
		Account toAccount = accountRepository.findById(toAccNum)
				.orElseThrow(()->new AccountNotFoundException(toAccNum + " not exist"));
		
		System.out.println("debit & credit..");

		if (fromAccount.getBalance() < amount)
			throw new BalanceException("current balance " + fromAccount.getBalance());

		fromAccount.setBalance(fromAccount.getBalance() - amount); // debit
		toAccount.setBalance(toAccount.getBalance() + amount); // credit
		
		Txn fromTxn = new Txn();
		Txn toTxn = new Txn();
		fromTxn.setAmount(fromAccount.getBalance());
		toTxn.setAmount(toAccount.getBalance());
		
		fromTxn.setRemarks("Debit");
		toTxn.setRemarks("credit");
		
		
		transactionRepository.save(fromTxn);
		transactionRepository.save(toTxn);
		
		System.out.println("New Transaction stored in to the database");

		accountRepository.save(fromAccount);
		accountRepository.save(toAccount);

		return true;

	}

}