package app;

import app.Services.WalletServices;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class App {
    public static Web3j web3j = null;

    public static void main(String[] args) {
        web3j = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
        WalletServices walletServices = new WalletServices();
        try {
            walletServices.sendEthers("961c162a4db6448b681eaae5dbece0a3771188c3688373fca6a2a5e619a43dff", "0x4CA299C91bb5C0cD27F6De7a673bE68d09c3Fd3D", "50", 20,
                    6721975);
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
