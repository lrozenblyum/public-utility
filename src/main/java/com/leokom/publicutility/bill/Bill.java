package com.leokom.publicutility.bill;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Bill {
	private final String serverResponse;

	public Bill( String serverResponse ) {
		this.serverResponse = serverResponse;
	}

	public String toPay() {
		//technically jsoup can also send a request to the server
		Document response = Jsoup.parse( serverResponse );

		return 
			response
			.getElementsContainingText("Сума до оплати")
			.stream()
			//searching for the next TD after "Сума до оплати"
			.filter(element -> element.is("TD"))
			.map(Element::nextElementSibling)
			.map(Element::text)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(" Failed to find what to pay from " + serverResponse));
	}
}