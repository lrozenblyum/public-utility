package com.leokom.publicutility.bill;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;
import org.junit.Test;

import com.leokom.publicutility.bill.Bill;

public class SiteToBillTest {
	@Test
	public void singleFieldParsed() throws IOException {
		String serverResponse = loadFile( "SampleResponse.html" );
		
		assertEquals( "10.18", new Bill( serverResponse ).toPay() );
	}

	private String loadFile( String path ) throws IOException {
		return 
			new TextOf( 
				new ResourceOf( path ) 
			).asString();
	}
}