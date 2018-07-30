package com.ethjava.LinkToken;

public class TransactionRecord {
	/**
	 * 转账金额
	 */
	private String amount;
	/**
	 * 交易所在区块
	 */
	private String blocknum;
	/**
	 * gas费用
	 */
	private String cost;
	/**
	 * 未知
	 */
	private String execAccount;
	/**
	 * 转账备注
	 */
	private String extra;
	/**
	 * 交易hash
	 */
	private String hash;
	/**
	 * 未知
	 */
	private String parent_hash;
	/**
	 * 状态 1 成功 0 失败
	 */
	private int status;
	/**
	 * 上链时间(10位时间戳)
	 */
	private long timestamp;
	/**
	 * 未知
	 */
	private String title;
	/**
	 * 对方地址
	 */
	private String tradeAccount;
	/**
	 * 0链克转出 1链克转入 2合约调用
	 */
	private int type;

	public TransactionRecord() {
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBlocknum() {
		return blocknum;
	}

	public void setBlocknum(String blocknum) {
		this.blocknum = blocknum;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getExecAccount() {
		return execAccount;
	}

	public void setExecAccount(String execAccount) {
		this.execAccount = execAccount;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getParent_hash() {
		return parent_hash;
	}

	public void setParent_hash(String parent_hash) {
		this.parent_hash = parent_hash;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTradeAccount() {
		return tradeAccount;
	}

	public void setTradeAccount(String tradeAccount) {
		this.tradeAccount = tradeAccount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "{" +
				"amount:" + amount +
				",blocknum:" + blocknum +
				",cost:" + cost +
				",execAccount:" + execAccount +
				",extra:" + extra +
				",hash:" + hash +
				",parent_hash:" + parent_hash +
				",status:" + status +
				",timestamp:" + timestamp +
				",title:" + title +
				",tradeAccount:" + tradeAccount +
				",type:" + type +
				"}\n";
	}
}
