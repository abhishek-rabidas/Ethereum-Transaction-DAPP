package app;

import app.Services.WalletServices;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;

public class App {
    public static Web3j web3j = null;

    public static void main(String[] args) {
        web3j = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
    }
}
