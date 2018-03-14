package com.ethjava;

import com.ethjava.utils.Environment;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 批量查询token余额
 */
public class TokenBalanceTask {

	public class Token {
		public String contractAddress;
		public int decimals;
		public String name;

		public Token(String contractAddress) {
			this.contractAddress = contractAddress;
			this.decimals = 0;
		}

		public Token(String contractAddress, int decimals) {
			this.contractAddress = contractAddress;
			this.decimals = decimals;
		}
	}

	private static Web3j web3j;

	//要查询的token合约地址
	private static List<Token> tokenList;

	//要查询的钱包地址
	private static List<String> addressList;

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		loadData();
		//如果没有decimals则需要请求
		requestDecimals();
		requestName();
		processTask();
	}


	private static void loadData() {
		tokenList = new ArrayList<>();
		// TODO: 2018/3/14 add...
		addressList = new ArrayList<>();
		// TODO: 2018/3/14 add...
	}

	private static void requestDecimals() {
		for (Token token : tokenList) {
			token.decimals = TokenClient.getTokenDecimals(web3j, token.contractAddress);
		}
	}

	private static void requestName() {
		for (Token token : tokenList) {
			token.name = TokenClient.getTokenName(web3j, token.contractAddress);
		}
	}

	private static void processTask() {
		for (String address : addressList) {
			for (Token token : tokenList) {
				BigDecimal balance = new BigDecimal(TokenClient.getTokenBalance(web3j, address, token.contractAddress));
				balance.divide(BigDecimal.TEN.pow(token.decimals));
				System.out.println("address " + address + " name " + token.name + " balance " + balance);
			}
		}
	}
}
