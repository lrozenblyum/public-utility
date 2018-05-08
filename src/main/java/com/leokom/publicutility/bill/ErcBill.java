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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Bill for <a href="http://erc.chv.ua/">Chernivtsi municipal info center</a>.
 *
 * @author Leonid Rozenblium (lrozenblyum@gmail.com)
 * @version $Id$
 * @since 0.0.1
 */
public final class ErcBill implements Bill {
    /**
     * Date formatter for Erc dates.
     * The class is thread-safe and immutable.
     */
    private static final DateTimeFormatter DATE_FORMATTER =
        DateTimeFormatter.ofPattern("dd.MM.yy");

    /**
     * Response from the server containing bill data.
     */
    private final String content;

    /**
     * Create a bill based on server response containing needed information.
     * @param content HTML content from the web site
     */
    public ErcBill(final String content) {
        this.content = content;
    }

    @Override
    public String toPay() {
        final Document response = Jsoup.parse(this.content);
        return this.loadCell(response, "Сума до оплати");
    }

    @Override
    public LocalDate date() {
        final Document response = Jsoup.parse(this.content);
        return ErcBill.stringToDate(
            this.loadCell(response, "Станом на")
        );
    }

    /**
     * Load cell value from a given response.
     * @param response Response from the server
     * @param name Cell name
     * @return Cell value
     */
    private String loadCell(final Document response, final String name) {
        return
            response.getElementsContainingText(name)
            .stream()
            .filter(element -> element.is("TD"))
            .map(Element::nextElementSibling)
            .map(Element::text)
            .findFirst()
            .orElseThrow(() -> this.generateNotFoundException(name));
    }

    /**
     * Convert a given string to date based on current Erc date format.
     * @param date Date in correct format
     * @return Java8 date
     */
    private static LocalDate stringToDate(final String date) {
        return LocalDate.parse(date, ErcBill.DATE_FORMATTER);
    }

    /**
     * Generate exception in case the desired element not found.
     * @param cell Cell name which value couldn't be found.
     * @return Instance of an exception
     */
    private IllegalArgumentException generateNotFoundException(
        final String cell) {
        return new IllegalArgumentException(
            String.format(" Failed to find %s from %s", cell, this.content)
        );
    }
}
