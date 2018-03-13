package com.ethjava;

import com.ethjava.utils.Environment;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;

public class EthInfo {
	private static Web3j web3j;

	public static void main(String[] args) {
		web3j = Web3j.build(new HttpService(Environment.RPC_URL));
		getEthInfo();
	}

	/**
	 * 请求区块链的信息
	 */
	private static void getEthInfo() {

		Web3ClientVersion web3ClientVersion = null;
		try {
			//客户端版本
			web3ClientVersion = web3j.web3ClientVersion().send();
			String clientVersion = web3ClientVersion.getWeb3ClientVersion();
			System.out.println("clientVersion " + clientVersion);
			//区块数量
			EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
			BigInteger blockNumber = ethBlockNumber.getBlockNumber();
			System.out.println(blockNumber);
			//挖矿奖励账户
			EthCoinbase ethCoinbase = web3j.ethCoinbase().send();
			String coinbaseAddress = ethCoinbase.getAddress();
			System.out.println(coinbaseAddress);
			//是否在同步区块
			EthSyncing ethSyncing = web3j.ethSyncing().send();
			boolean isSyncing = ethSyncing.isSyncing();
			System.out.println(isSyncing);
			//是否在挖矿
			EthMining ethMining = web3j.ethMining().send();
			boolean isMining = ethMining.isMining();
			System.out.println(isMining);
			//当前gas price
			EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
			BigInteger gasPrice = ethGasPrice.getGasPrice();
			System.out.println(gasPrice);
			//挖矿速度
			EthHashrate ethHashrate = web3j.ethHashrate().send();
			BigInteger hashRate = ethHashrate.getHashrate();
			System.out.println(hashRate);
			//协议版本
			EthProtocolVersion ethProtocolVersion = web3j.ethProtocolVersion().send();
			String protocolVersion = ethProtocolVersion.getProtocolVersion();
			System.out.println(protocolVersion);
			//连接的节点数
			NetPeerCount netPeerCount = web3j.netPeerCount().send();
			BigInteger peerCount = netPeerCount.getQuantity();
			System.out.println(peerCount);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
