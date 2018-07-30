package com.ethjava.LinkToken;

import org.web3j.protocol.core.Request;

public interface LinkToken {
	Request<?, LinkTransactionRecord> linkTransactionRecord(String address, int page, int count);

}
