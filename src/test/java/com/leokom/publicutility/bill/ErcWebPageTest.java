package com.leokom.publicutility.bill;

import static org.junit.Assert.assertThat;
import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

/**
 * Test the web page.
 * Technically it should be some kind of an Integrational Test (since it can be red while the feature is working fine).
 * @author leokom
 *
 */
public class ErcWebPageTest {
    @Test
    public void basicWebSite() throws IOException {
        int account=21820;
        assertThat(new ErcWebPage(account).asString(), CoreMatchers.containsString("21820"));
    }
    
    @Test
    public void webSiteContainsCorrectAddress() throws IOException {
        assertThat(new ErcWebPage(21805).asString(), CoreMatchers.containsString("Миколайчука Iвана"));
    }
}
