/**
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
import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test Erc Bill.
 * @author Leonid Rozenblium (lrozenblyum@gmail.com)
 * @version $Id$
 * @since 0.0.1
 */
public final class ErcBillTest {
    /**
     * Response with the first value of 'toPay'.
     */
    private static final String FIRST_RESPONSE =
        "SampleResponse.html";
    /**
     * Response with the second value of 'toPay'.
     */
    private static final String SECOND_RESPONSE =
        "SampleResponseAnotherPayment.html";

    /**
     * Check that a single field (to pay) can be parsed.
     */
    @Test
    public void singleFieldParsed() {
        final String response = this.loadFile(FIRST_RESPONSE);
        Assert.assertEquals("10.18", new ErcBill(response).toPay());
    }

    /**
     * Check that a single field (to pay) with another value can be parsed.
     */
    @Test
    public void singleFileParsedTriangulate() {
        final String response = this.loadFile(SECOND_RESPONSE);
        Assert.assertEquals("988.17", new ErcBill(response).toPay());
    }

    /**
     * Load a file from a given path accessible for the current class loader.
     * @param path Path to the file
     * @return File content
     */
    private String loadFile(final String path) {
        try {
            return new TextOf(new ResourceOf(path)).asString();
        } catch (final IOException exception) {
            throw new IllegalStateException(
                String.format("Failed to load",
                path
            ), exception);
        }
    }
}
