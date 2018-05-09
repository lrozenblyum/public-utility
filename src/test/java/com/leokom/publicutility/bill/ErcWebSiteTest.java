package com.leokom.publicutility.bill;

import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

/**
 * Test the website.
 * Technically it should be some kind of an Integrational Test (since it can be red while the feature is working fine).
 * @author leokom
 *
 */
public class ErcWebSiteTest {
    @Test
    public void basicWebSite() throws IOException {
        int account=21820;
        assertThat(new ErcWebSite(account).load(), CoreMatchers.containsString("21820"));
    }
    
    @Test
    public void webSiteContainsCorrectAddress() throws IOException {
        assertThat(new ErcWebSite(21805).load(), CoreMatchers.containsString("Миколайчука Iвана"));
    }
}
