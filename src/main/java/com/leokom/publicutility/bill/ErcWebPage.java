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

import com.jcabi.http.Request;
import com.jcabi.http.request.JdkRequest;
import java.io.IOException;
import org.cactoos.Text;

/**
 * Represent real webpage from the erc website.
 * @since 0.0.1
 */
public final class ErcWebPage implements Text {
    /**
     * Id of the warm supplier provided (just for demo purposes).
     */
    private static final int DEMO_SUPPLIER_ID = 37;

    /**
     * Account number in the warm supplier.
     */
    private final int account;

    /**
     * Erc Web page for the given account in TeploKomunEnergo.
     * @param account Account number in TeploKomunEnergo
     */
    public ErcWebPage(final int account) {
        this.account = account;
    }

    @Override
    public String asString() throws IOException {
        return new JdkRequest("http://erc.chv.ua/borg/index.php")
            .method(Request.POST)
            .body()
            .formParam("osr", String.valueOf(this.account))
            .formParam("org", ErcWebPage.DEMO_SUPPLIER_ID)
            .back()
            .fetch()
            .body();
    }

}
