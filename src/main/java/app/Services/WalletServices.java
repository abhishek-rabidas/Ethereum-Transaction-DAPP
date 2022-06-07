package app.Services;

import app.App;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import static app.App.web3j;

public class WalletServices {

    public void checkBalance(String address) throws IOException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address,
                DefaultBlockParameterName.LATEST).send();

        System.out.println("Bal: " + Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
    }

    public void sendEthers(String senderPrivateKey, String recipientAddress, String amount, int gasPrice, int gasLimit) throws IOException, ExecutionException,
            InterruptedException {
        Credentials credentials = Credentials.create(senderPrivateKey);

        System.out.println("Current Balance: "
                + Convert.fromWei(web3j.ethGetBalance(credentials.getAddress(), DefaultBlockParameterName.LATEST)
                .send().getBalance().toString(), Convert.Unit.ETHER));

        EthGetTransactionCount ethGetTransactionCount = web3j
                .ethGetTransactionCount(credentials.getAddress(), DefaultBlockParameterName.LATEST).send();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();

        BigInteger value = Convert.toWei(amount, Convert.Unit.ETHER).toBigInteger();


        RawTransaction rawTransaction = RawTransaction.createEtherTransaction(nonce, Convert.toWei(String.valueOf(gasPrice), Convert.Unit.GWEI).toBigInteger(), BigInteger.valueOf(gasLimit),
                recipientAddress, value);

        byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        String hexValue = Numeric.toHexString(signedMessage);

        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();
        String transactionHash = ethSendTransaction.getTransactionHash();
        System.out.println("transactionHash: " + transactionHash);

    }
}
