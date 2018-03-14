package com.ethjava;

import com.ethjava.utils.Environment;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于ERC20的代币
 */
public class TokenClient {

	private static Web3j web3j;

	private static String fromAddress = "0x7b1cc408fcb2de1d510c1bf46a329e9027db4112";

	private static String contractAddress = "0x4c1ae77bc2df45fb68b13fa1b4f000305209b0cb";

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		getTokenBalance();
	}

	/**
	 * 查询代币余额
	 */
	private static void getTokenBalance() {

		String methodName = "balanceOf";
		List<Type> inputParameters = new ArrayList<>();
		List<TypeReference<?>> outputParameters = new ArrayList<>();
		Address address = new Address(fromAddress);
		inputParameters.add(address);

		TypeReference<Uint256> typeReference = new TypeReference<Uint256>() {
		};
		outputParameters.add(typeReference);
		Function function = new Function(methodName, inputParameters, outputParameters);
		String data = FunctionEncoder.encode(function);
		Transaction transaction = Transaction.createEthCallTransaction(fromAddress, contractAddress, data);

		EthCall ethCall;
		try {
			ethCall = web3j.ethCall(transaction, DefaultBlockParameterName.LATEST).send();
			List<Type> results = FunctionReturnDecoder.decode(ethCall.getValue(), function.getOutputParameters());
			BigInteger balanceValue = (BigInteger) results.get(0).getValue();
			String balance = balanceValue.toString();
			System.out.println(balance);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
