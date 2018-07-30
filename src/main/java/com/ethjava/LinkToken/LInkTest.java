package com.ethjava.LinkToken;

import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class LInkTest {

	public static String LINK_URL_TEST = "https://sandbox-walletapi.onethingpcs.com/";
	public static String LINK_URL_MAIN = "https://walletapi.onethingpcs.com/";

	public static void main(String[] args) {

		JsonRpcLinkWeb3j linkWeb3j = new JsonRpcLinkWeb3j(new LinkHttpService(LINK_URL_TEST));
//		JsonRpcLinkWeb3j linkWeb3j = new JsonRpcLinkWeb3j(new LinkHttpService(LINK_URL_MAIN));

		String address = "0x6c0f49af552f2326dd851b68832730cb7b6c0daf";

		BigInteger balance = null;

		try {
			EthGetBalance ethGetBalance = linkWeb3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
			balance = ethGetBalance.getBalance();
			System.out.println("address " + address + " balance " + balance + " wei");

			EthGetTransactionCount ethGetTransactionCount = linkWeb3j.ethGetTransactionCount(address, DefaultBlockParameterName.PENDING).send();
			BigInteger nonce = ethGetTransactionCount.getTransactionCount();
			System.out.println("address " + address + " nonce " + nonce);

			LinkTransactionRecord linkTransactionRecord = linkWeb3j.linkTransactionRecord(address, 1, 40).send();
			List<TransactionRecord> transactionRecord = linkTransactionRecord.getTransactionRecord();
			System.out.println("address " + address + " record " + Arrays.toString(transactionRecord.toArray()));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
