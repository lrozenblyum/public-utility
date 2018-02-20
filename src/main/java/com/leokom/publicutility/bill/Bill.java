package com.leokom.publicutility.bill;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Bill {
	private final String serverResponse;

	public Bill( String serverResponse ) {
		this.serverResponse = serverResponse;
	}

	public String toPay() {
		Document response = Jsoup.parse( serverResponse );
		
		return response.getElementsContainingText("Сума до оплати").stream().
		filter( element -> element.is("TD") )
		.map( element -> element.nextElementSibling() )
		.map( element -> element.text() )
		.findFirst().orElseThrow(() -> new IllegalArgumentException(" Failed to find what to pay from " + serverResponse ) );
	}
}