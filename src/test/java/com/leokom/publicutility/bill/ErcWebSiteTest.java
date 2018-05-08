package com.leokom.publicutility.bill;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

public class ErcWebSiteTest {
    @Test
    public void basicWebSite() {
        int account=45011;
        assertThat(new ErcWebSite(account).load(), CoreMatchers.containsString("45011"));
    }
    
    @Test
    public void webSiteContainsCorrectAddress() {
        assertThat(new ErcWebSite(21805).load(), CoreMatchers.containsString("Миколайчука Iвана"));
    }
}
