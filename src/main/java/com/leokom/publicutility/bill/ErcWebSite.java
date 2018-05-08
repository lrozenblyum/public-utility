package com.leokom.publicutility.bill;

public class ErcWebSite {
    private int account;

    public ErcWebSite(int account) {
        this.account=account;
    }

    public String load() {
        return String.valueOf(this.account);
    }

}
