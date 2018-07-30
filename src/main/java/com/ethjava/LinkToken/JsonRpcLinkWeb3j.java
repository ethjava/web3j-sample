package com.ethjava.LinkToken;

import org.web3j.protocol.Web3jService;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.Request;

import java.util.Arrays;

public class JsonRpcLinkWeb3j extends JsonRpc2_0Web3j implements LinkToken {
	public JsonRpcLinkWeb3j(Web3jService web3jService) {
		super(web3jService);
	}

	@Override
	public Request<?, LinkTransactionRecord> linkTransactionRecord(String address, int page, int count) {
		return new Request<>(
				"getTransactionRecords",
				Arrays.asList(address, "0", "0", String.valueOf(page), String.valueOf(count)),
				web3jService,
				LinkTransactionRecord.class);
	}

}
