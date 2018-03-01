package com.leokom.publicutility.bill;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Bill for <a href="http://erc.chv.ua/">Chernivtsi municipal info center</a>
 * 
 * @author Leonid
 *
 */
public final class ErcBill {
    private final String content;

    /**
     * Create a bill based on server response containing needed information.
     * 
     * @param content html content from the web site
     */
    public ErcBill(String content) {
        this.content = content;
    }

    /**
     * Tell how many we need to pay for the service.
     * 
     * @return Amount of money to pay
     */
    public String toPay() {
        // technically jsoup can also send a request to the server
        final Document response = Jsoup.parse(content);

        return 
                response.getElementsContainingText("Сума до оплати").
                stream()
                // searching for the next TD after "Сума до оплати"
                .filter(element -> element.is("TD")).map(Element::nextElementSibling)
                .map(Element::text)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                    String.format(" Failed to find what to pay from %s", content)
                 ));
    }
}
