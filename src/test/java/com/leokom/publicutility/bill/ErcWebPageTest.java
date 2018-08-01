/*
 * Copyright 5778 Leonid Rozenblium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leokom.publicutility.bill;

import java.io.IOException;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test the web page.
 * Technically it should be some kind of an Integrational Test
 * (since it can be red while the feature is working fine).
 * @since 0.0.1
 */
public final class ErcWebPageTest {
    /**
     * Load the web-site and verify response
     *  matching the account id in the request.
     * @throws IOException in case the web site is not reachable
     */
    @Test
    public void basicWebSite() throws IOException {
        MatcherAssert.assertThat(
            new ErcWebPage(21820).asString(),
            CoreMatchers.containsString("21820")
        );
    }

    /**
     * Load the web-site and verify response contains a correct address.
     * @throws IOException in case the web site is not reachable
     */
    @Test
    public void webSiteContainsCorrectAddress() throws IOException {
        MatcherAssert.assertThat(
            new ErcWebPage(21805).asString(), 
            CoreMatchers.containsString("Миколайчука Iвана")
        );
    }
}
