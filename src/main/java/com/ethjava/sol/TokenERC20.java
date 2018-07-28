package com.ethjava.sol;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class TokenERC20 extends Contract {
    private static final String BINARY = "60806040526002805460ff1916601217905534801561001d57600080fd5b506040516109c63803806109c683398101604090815281516020808401518385015160025460ff16600a0a84026003819055336000908152600485529586205590850180519395909491019261007592850190610092565b508051610089906001906020840190610092565b5050505061012d565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106100d357805160ff1916838001178555610100565b82800160010185558215610100579182015b828111156101005782518255916020019190600101906100e5565b5061010c929150610110565b5090565b61012a91905b8082111561010c5760008155600101610116565b90565b61088a8061013c6000396000f3006080604052600436106100b95763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde0381146100be578063095ea7b31461014857806318160ddd1461018057806323b872dd146101a7578063313ce567146101d157806342966c68146101fc57806370a082311461021457806379cc67901461023557806395d89b4114610259578063a9059cbb1461026e578063cae9ca5114610294578063dd62ed3e146102fd575b600080fd5b3480156100ca57600080fd5b506100d3610324565b6040805160208082528351818301528351919283929083019185019080838360005b8381101561010d5781810151838201526020016100f5565b50505050905090810190601f16801561013a5780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561015457600080fd5b5061016c600160a060020a03600435166024356103b2565b604080519115158252519081900360200190f35b34801561018c57600080fd5b506101956103df565b60408051918252519081900360200190f35b3480156101b357600080fd5b5061016c600160a060020a03600435811690602435166044356103e5565b3480156101dd57600080fd5b506101e6610454565b6040805160ff9092168252519081900360200190f35b34801561020857600080fd5b5061016c60043561045d565b34801561022057600080fd5b50610195600160a060020a03600435166104d5565b34801561024157600080fd5b5061016c600160a060020a03600435166024356104e7565b34801561026557600080fd5b506100d36105b8565b34801561027a57600080fd5b50610292600160a060020a0360043516602435610612565b005b3480156102a057600080fd5b50604080516020600460443581810135601f810184900484028501840190955284845261016c948235600160a060020a03169460248035953695946064949201919081908401838280828437509497506106219650505050505050565b34801561030957600080fd5b50610195600160a060020a036004358116906024351661073a565b6000805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103aa5780601f1061037f576101008083540402835291602001916103aa565b820191906000526020600020905b81548152906001019060200180831161038d57829003601f168201915b505050505081565b336000908152600560209081526040808320600160a060020a039590951683529390529190912055600190565b60035481565b600160a060020a038316600090815260056020908152604080832033845290915281205482111561041557600080fd5b600160a060020a038416600090815260056020908152604080832033845290915290208054839003905561044a848484610757565b5060019392505050565b60025460ff1681565b3360009081526004602052604081205482111561047957600080fd5b3360008181526004602090815260409182902080548690039055600380548690039055815185815291517fcc16f5dbb4873280815c1ee09dbd06736cffcc184412cf7a71a0fdb75d397ca59281900390910190a2506001919050565b60046020526000908152604090205481565b600160a060020a03821660009081526004602052604081205482111561050c57600080fd5b600160a060020a038316600090815260056020908152604080832033845290915290205482111561053c57600080fd5b600160a060020a0383166000818152600460209081526040808320805487900390556005825280832033845282529182902080548690039055600380548690039055815185815291517fcc16f5dbb4873280815c1ee09dbd06736cffcc184412cf7a71a0fdb75d397ca59281900390910190a250600192915050565b60018054604080516020600284861615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156103aa5780601f1061037f576101008083540402835291602001916103aa565b61061d338383610757565b5050565b60008361062e81856103b2565b15610732576040517f8f4ffcb10000000000000000000000000000000000000000000000000000000081523360048201818152602483018790523060448401819052608060648501908152875160848601528751600160a060020a03871695638f4ffcb195948b94938b939192909160a490910190602085019080838360005b838110156106c65781810151838201526020016106ae565b50505050905090810190601f1680156106f35780820380516001836020036101000a031916815260200191505b5095505050505050600060405180830381600087803b15801561071557600080fd5b505af1158015610729573d6000803e3d6000fd5b50505050600191505b509392505050565b600560209081526000928352604080842090915290825290205481565b6000600160a060020a038316151561076e57600080fd5b600160a060020a03841660009081526004602052604090205482111561079357600080fd5b600160a060020a038316600090815260046020526040902054828101116107b957600080fd5b50600160a060020a038083166000818152600460209081526040808320805495891680855282852080548981039091559486905281548801909155815187815291519390950194927fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef929181900390910190a3600160a060020a0380841660009081526004602052604080822054928716825290205401811461085857fe5b505050505600a165627a7a72305820a699795c1a251457c903be9b4228f16928a18279fb1c6adc9eadefb8a46ae9060029";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_BURN = "burn";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_BURNFROM = "burnFrom";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_APPROVEANDCALL = "approveAndCall";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    public static final Event BURN_EVENT = new Event("Burn", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    protected TokenERC20(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TokenERC20(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new Address(_spender),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new Address(_from),
                new Address(_to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> burn(BigInteger _value) {
        final Function function = new Function(
                FUNC_BURN, 
                Arrays.<Type>asList(new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balanceOf(String param0) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(param0)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> burnFrom(String _from, BigInteger _value) {
        final Function function = new Function(
                FUNC_BURNFROM, 
                Arrays.<Type>asList(new Address(_from),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new Address(_to),
                new Uint256(_value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> approveAndCall(String _spender, BigInteger _value, byte[] _extraData) {
        final Function function = new Function(
                FUNC_APPROVEANDCALL, 
                Arrays.<Type>asList(new Address(_spender),
                new Uint256(_value),
                new org.web3j.abi.datatypes.DynamicBytes(_extraData)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> allowance(String param0, String param1) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new Address(param0),
                new Address(param1)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<TokenERC20> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialSupply, String tokenName, String tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(initialSupply),
                new Utf8String(tokenName),
                new Utf8String(tokenSymbol)));
        return deployRemoteCall(TokenERC20.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<TokenERC20> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, BigInteger initialSupply, String tokenName, String tokenSymbol) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Uint256(initialSupply),
                new Utf8String(tokenName),
                new Utf8String(tokenSymbol)));
        return deployRemoteCall(TokenERC20.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventObservable(filter);
    }

    public List<BurnEventResponse> getBurnEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(BURN_EVENT, transactionReceipt);
        ArrayList<BurnEventResponse> responses = new ArrayList<BurnEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            BurnEventResponse typedResponse = new BurnEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<BurnEventResponse> burnEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, BurnEventResponse>() {
            @Override
            public BurnEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(BURN_EVENT, log);
                BurnEventResponse typedResponse = new BurnEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<BurnEventResponse> burnEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BURN_EVENT));
        return burnEventObservable(filter);
    }

    public static TokenERC20 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenERC20(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static TokenERC20 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TokenERC20(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class TransferEventResponse {
        public Log log;

        public String from;

        public String to;

        public BigInteger value;
    }

    public static class BurnEventResponse {
        public Log log;

        public String from;

        public BigInteger value;
    }
}
