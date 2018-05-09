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
import java.math.BigDecimal;
import java.time.LocalDate;
import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * Test Erc Bill.
 * @since 0.0.1
 */
public final class ErcBillTest {
    /**
     * Id of a valid account inside the supported supplier.
     */
    private static final int VALID_ACCOUNT_ID = 21800;

    /**
     * Date in the first response file.
     */
    private static final LocalDate HARD_CODED_DATE =
        LocalDate.of(2018, 1, 1);

    /**
     * Date in the March file.
     */
    private static final LocalDate MARCH =
        LocalDate.of(2018, 3, 1);
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
     * Response with the results for March.
     */
    private static final String RESPONSE_MARCH =
        "ResponseMarch2018.html";

    /**
     * Check that a real bill can be parsed.
     * Technically this is some kind of 'IT' tests
     * @throws IOException in case the web page is not reachable
     */
    @Test
    public void realBillCanBeParsed() throws IOException {
        MatcherAssert.assertThat(
            new ErcBill(
                new ErcWebPage(ErcBillTest.VALID_ACCOUNT_ID)
            ).toPay(),
            CoreMatchers.is(CoreMatchers.notNullValue())
        );
    }

    /**
     * Check that a single field (to pay) can be parsed.
     */
    @Test
    public void singleFieldParsed() {
        final Bill bill = this.loadBill(ErcBillTest.FIRST_RESPONSE);
        MatcherAssert.assertThat(
            bill.toPay(),
            CoreMatchers.equalTo(new BigDecimal("10.18"))
        );
    }

    /**
     * Check that a single field (to pay) with another value can be parsed.
     */
    @Test
    public void singleFileParsedTriangulate() {
        final Bill bill = this.loadBill(ErcBillTest.SECOND_RESPONSE);
        MatcherAssert.assertThat(
            bill.toPay(),
            CoreMatchers.equalTo(new BigDecimal("988.17"))
        );
    }

    /**
     * Check that date can be parsed.
     */
    @Test
    public void dateParsed() {
        final Bill bill = this.loadBill(ErcBillTest.FIRST_RESPONSE);
        MatcherAssert.assertThat(
            bill.date(),
            CoreMatchers.equalTo(ErcBillTest.HARD_CODED_DATE)
        );
    }

    /**
     * Check that March date can be parsed.
     */
    @Test
    public void dateParsedTriangulate() {
        final Bill bill = this.loadBill(ErcBillTest.RESPONSE_MARCH);
        MatcherAssert.assertThat(
            bill.date(),
            CoreMatchers.equalTo(ErcBillTest.MARCH)
        );
    }

    /**
     * Load the bill from the file.
     * @param path Path to the file to be loaded
     * @return Bill.
     */
    private Bill loadBill(final String path) {
        final String response = this.loadFile(path);
        return new ErcBill(response);
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
                String.format(
                    "Failed to load",
                    path
                ),
                exception
            );
        }
    }
}
