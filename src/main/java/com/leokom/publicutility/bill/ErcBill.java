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
        return
            response.getElementsContainingText("Сума до оплати")
            .stream()
            .filter(element -> element.is("TD"))
            .map(Element::nextElementSibling)
            .map(Element::text)
            .findFirst()
            .orElseThrow(this::generateNotFoundException);
    }

    @Override
    public LocalDate date() {
        final Document response = Jsoup.parse(this.content);
        return
            response.getElementsContainingText("Станом на")
            .stream()
            .filter(element -> element.is("TD"))
            .map(Element::nextElementSibling)
            .map(Element::text)
            .findFirst()
            .map(ErcBill::stringToDate)
            .orElseThrow(this::generateNotFoundException);
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
     * @return Instance of an exception
     */
    private IllegalArgumentException generateNotFoundException() {
        return new IllegalArgumentException(
            String.format(" Failed to find what to pay from %s", this.content)
        );
    }
}
