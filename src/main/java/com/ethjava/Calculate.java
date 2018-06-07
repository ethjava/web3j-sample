package com.ethjava;

import org.web3j.crypto.Hash;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.utils.Numeric;

import java.util.Arrays;

public class Calculate {
	public static void main(String[] args) {
		System.out.println(calculateContractAddress("0x6c0f49aF552F2326DD851b68832730CB7b6C0DaF".toLowerCase(), 294));

		String signedData = "0xf8ac8201518506fc23ac00830493e094fda023cea60a9f421d74ac49f9a015880a77dd7280b844a9059cbb000000000000000000000000b5dbd2e4093a501f1d1e645f04cef5815a1581d7000000000000000000000000000000000000000000000004c53ecdc18a6000001ca03d710f3c5aabde2733938c44c0b1448f96e760c030205562f59889557397faa4a007110abbcfa343381a2f713d6339d3fa751200f82cc2f06a4d1967b4eaf61d50";
		System.out.println(caculateTransactionHash(signedData));
	}

	/**
	 * 发布前 计算合约地址
	 */
	private static String calculateContractAddress(String address, long nonce) {
		//样例 https://ropsten.etherscan.io/tx/0x728a95b02beec3de9fb09ede00ca8ca6939bad2ad26c702a8392074dc04844c7
		byte[] addressAsBytes = Numeric.hexStringToByteArray(address);

		byte[] calculatedAddressAsBytes =
				Hash.sha3(RlpEncoder.encode(
						new RlpList(
								RlpString.create(addressAsBytes),
								RlpString.create((nonce)))));

		calculatedAddressAsBytes = Arrays.copyOfRange(calculatedAddressAsBytes,
				12, calculatedAddressAsBytes.length);
		String calculatedAddressAsHex = Numeric.toHexString(calculatedAddressAsBytes);
		return calculatedAddressAsHex;
	}

	/**
	 * 提交前 计算交易hash
	 */
	private static String caculateTransactionHash(String signedData) {
		//样例 https://ropsten.etherscan.io/tx/0xfd8acd10d72127f29f0a01d8bcaf0165665b5598781fe01ca4bceaa6ab9f2cb0
		String txHash = Hash.sha3(signedData);
		return txHash;
	}
}
