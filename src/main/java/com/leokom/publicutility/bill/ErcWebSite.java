package com.leokom.publicutility.bill;

import java.io.IOException;

import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;

public class ErcWebSite {
    private static final int ID_OF_TEPLOKOMUNENERGO = 37;
    private int account;

    public ErcWebSite(int account) {
        this.account=account;
    }

    public String load() throws IOException {
        return new JdkRequest("http://erc.chv.ua/borg/index.php").method(Request.POST)
                .body()
                .formParam("osr", String.valueOf(account))
                .formParam("org", ID_OF_TEPLOKOMUNENERGO)
                .back()
                .fetch()
                .body();
    }

}
