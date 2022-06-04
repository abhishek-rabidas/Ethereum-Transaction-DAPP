package app;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        Web3j web3j = Web3j.build(new HttpService("HTTP://127.0.0.1:7545"));
        Web3ClientVersion web3ClientVersion = null;
        try {
            web3ClientVersion = web3j.web3ClientVersion().send();
            System.out.println("Web 3 Client Version: " + web3ClientVersion.getWeb3ClientVersion());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
