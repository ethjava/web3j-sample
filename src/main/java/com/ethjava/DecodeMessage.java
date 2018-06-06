package com.ethjava;

import org.web3j.crypto.Keys;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.rlp.RlpDecoder;
import org.web3j.rlp.RlpList;
import org.web3j.rlp.RlpString;
import org.web3j.rlp.RlpType;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.List;

public class DecodeMessage {

	/**
	 * 通过签名后会得到一个加密后的字符串
	 * 本类将分析这个字符串
	 */
	public static void main(String[] args) {
		String signedData = "0xf8a4120f82ea60944c1ae77bc2df45fb68b13fa1b4f000305209b0cb80b844a9059cbb000000000000000000000000c9e1718c3168474b37969ac1aa5602cceef0cd1600000000000000000000000000000000000000000000000000000000000003e81ba062091870dc1d5cdfd5a9fb7422df0321ada586de85df8e3e89ed7b3773cfc393a00a121d34b41bfc15c8dab4c69b7247ac7066cb38a256cc4856d57558dce78f7d";
		decodeMessage(signedData);

	}

	private static void decodeMessage(String signedData) {
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
			RawTransaction rawTransaction1 = RawTransaction.createTransaction(nonce, gasPrice, gasLimit, to, value, data);
			RlpString v = (RlpString) values.get(6);
			RlpString r = (RlpString) values.get(7);
			RlpString s = (RlpString) values.get(8);
			Sign.SignatureData signatureData = new Sign.SignatureData(v.getBytes()[0], r.getBytes(), s.getBytes());
			BigInteger pubKey = Sign.signedMessageToKey(TransactionEncoder.encode(rawTransaction1), signatureData);
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
}
