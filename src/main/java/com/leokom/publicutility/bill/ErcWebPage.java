package com.leokom.publicutility.bill;

import java.io.IOException;
import org.cactoos.Text;
import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;

/**
 * Represent real webpage from the erc website
 * @author leokom
 *
 */
public class ErcWebPage implements Text {
    private static final int ID_OF_TEPLOKOMUNENERGO = 37;
    private int account;

    public ErcWebPage(int account) {
        this.account=account;
    }

    @Override
    public String asString() throws IOException {
        return new JdkRequest("http://erc.chv.ua/borg/index.php").method(Request.POST)
                .body()
                .formParam("osr", String.valueOf(account))
                .formParam("org", ID_OF_TEPLOKOMUNENERGO)
                .back()
                .fetch()
                .body();
    }

}
