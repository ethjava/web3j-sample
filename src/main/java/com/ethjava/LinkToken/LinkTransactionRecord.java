package com.ethjava.LinkToken;

import org.web3j.protocol.core.Response;

import java.util.List;

public class LinkTransactionRecord extends Response<List<TransactionRecord>> {

	public List<TransactionRecord> getTransactionRecord() {
		return getResult();
	}
}