package com.ethjava;

import com.ethjava.utils.Environment;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;

import java.util.Arrays;
import java.util.List;

/**
 * Event log相关
 * 监听合约event
 */
public class ContractEvent {
	private static String contractAddress = "0x4c1ae77bc2df45fb68b13fa1b4f000305209b0cb";
	private static Web3j web3j;

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		/**
		 * 监听ERC20 token 交易
		 */
		EthFilter filter = new EthFilter(
				DefaultBlockParameterName.EARLIEST,
				DefaultBlockParameterName.LATEST,
				contractAddress);
		Event event = new Event("Transfer",
				Arrays.<TypeReference<?>>asList(
						new TypeReference<Address>(true) {
						},
						new TypeReference<Address>(true) {
						}, new TypeReference<Uint256>(false) {
						}
				)
		);

		String topicData = EventEncoder.encode(event);
		filter.addSingleTopic(topicData);
		System.out.println(topicData);

		web3j.ethLogObservable(filter).subscribe(log -> {
			System.out.println(log.getBlockNumber());
			System.out.println(log.getTransactionHash());
			List<String> topics = log.getTopics();
			for (String topic : topics) {
				System.out.println(topic);
			}
		});
	}
}
