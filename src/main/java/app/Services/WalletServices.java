package app.Services;

import app.App;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.utils.Convert;

import java.io.IOException;

import static app.App.web3j;

public class WalletServices {

    public void checkBalance() throws IOException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance("0xae3D11BB19ce8Fbe1279041Db209fc70F4C09E9f",
                DefaultBlockParameterName.LATEST).send();

        System.out.println("Bal: " + Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
    }
}
