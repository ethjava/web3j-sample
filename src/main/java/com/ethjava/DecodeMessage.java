package com.ethjava;

import org.web3j.crypto.*;
import org.web3j.rlp.RlpDecoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.security.SignatureException;
import java.util.List;

public class DecodeMessage {

	/**
	 * 通过签名后会得到一个加密后的字符串
	 * 本类将分析这个字符串
	 */
	public static void main(String[] args) {
		String signedData = "0xf8ac8201518506fc23ac00830493e094fda023cea60a9f421d74ac49f9a015880a77dd7280b844a9059cbb000000000000000000000000b5dbd2e4093a501f1d1e645f04cef5815a1581d7000000000000000000000000000000000000000000000004c53ecdc18a6000001ca03d710f3c5aabde2733938c44c0b1448f96e760c030205562f59889557397faa4a007110abbcfa343381a2f713d6339d3fa751200f82cc2f06a4d1967b4eaf61d50";
		decodeMessage(signedData);
		decodeMessageV340(signedData);
	}

	private static void decodeMessage(String signedData) {
		//样例 https://ropsten.etherscan.io/tx/0xfd8acd10d72127f29f0a01d8bcaf0165665b5598781fe01ca4bceaa6ab9f2cb0
		try {
			System.out.println(signedData);
			System.out.println("解密 start " + System.currentTimeMillis());
			RlpList rlpList = RlpDecoder.decode(Numeric.hexStringToByteArray(signedData));
			List<RlpType> values = ((RlpList) rlpList.getValues().get(0)).getValues();
			BigInteger nonce = Numeric.toBigInt(((RlpString) values.get(0)).getBytes());
			BigInteger gasPrice = Numeric.toBigInt(((RlpString) values.get(1)).getBytes());
			BigInteger gasLimit = Numeric.toBigInt(((RlpString) values.get(2)).getBytes());
			String to = Numeric.toHexString(((RlpString) values.get(3)).getBytes());
			BigInteger value = Numeric.toBigInt(((RlpString) values.get(4)).getBytes());
			String data = Numeric.toHexString(((RlpString) values.get(5)).getBytes());
			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data);
			RlpString v = (RlpString) values.get(6);
			RlpString r = (RlpString) values.get(7);
			RlpString s = (RlpString) values.get(8);
			Sign.SignatureData signatureData = new Sign.SignatureData(
					v.getBytes()[0],
					Numeric.toBytesPadded(Numeric.toBigInt(r.getBytes()), 32),
					Numeric.toBytesPadded(Numeric.toBigInt(s.getBytes()), 32));
			BigInteger pubKey = Sign.signedMessageToKey(TransactionEncoder.encode(rawTransaction), signatureData);
			System.out.println("publicKey " + pubKey.toString(16));
			String address = Numeric.prependHexPrefix(Keys.getAddress(pubKey));
			System.out.println("address " + address);
			System.out.println("解密 end " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 可以看到交易数据本身是没有加密的，是可以直接获取到。
	 * v r s是用私钥加密的数据，利用v r s加上交易数据可以得到私钥对应的公钥及地址。
	 * 所以RawTransaction里是没有fromAddress的参数的。
	 * 解密出的地址就是发出交易的地址。这样一来完成了验证。
	 */
	private static void decodeMessageV340(String signedData) {
		System.out.println("解密 start " + System.currentTimeMillis());
		RawTransaction rawTransaction = TransactionDecoder.decode(signedData);
		if (rawTransaction instanceof SignedRawTransaction) {
			try {
				String from = ((SignedRawTransaction) rawTransaction).getFrom();
				System.out.println("address " + from);
			} catch (SignatureException e) {
				e.printStackTrace();
			}
		}
		System.out.println("解密 end " + System.currentTimeMillis());
	}
}
