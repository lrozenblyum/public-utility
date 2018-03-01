package com.leokom.publicutility.bill;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Bill for public utility services.
 * 
 * @author Leonid
 *
 */
public final class Bill {
    private final String serverResponse;

    /**
     * Create a bill based on server response containing needed information.
     * 
     * @param serverResponse
     *            response from a public utility service
     */
    public Bill(String serverResponse) {
        this.serverResponse = serverResponse;
    }

    /**
     * Tell how many we need to pay for the service.
     * 
     * @return Amount of money to pay
     */
    public String toPay() {
        // technically jsoup can also send a request to the server
        final Document response = Jsoup.parse(serverResponse);

        return 
                response.getElementsContainingText("Сума до оплати").
                stream()
                // searching for the next TD after "Сума до оплати"
                .filter(element -> element.is("TD")).map(Element::nextElementSibling)
                .map(Element::text)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                    String.format(" Failed to find what to pay from %s", serverResponse)
                 ));
    }
}
