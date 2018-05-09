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

    /**
     * Create Erc Web page for the given account in TeploKomunEnergo
     * @param account Account number in TeploKomunEnergo
     */
    public ErcWebPage(int account) {
        this.account=account;
    }

    /**
     * Load the Web page content for the account
     * @throws IOException if the website cannot be loaded
     */
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
