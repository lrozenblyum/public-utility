package com.leokom.publicutility.bill;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.cactoos.io.ResourceOf;
import org.cactoos.text.TextOf;
import org.junit.Test;

public class ErcBillTest {
    @Test
    public void singleFieldParsed() throws IOException {
        String serverResponse = this.loadFile("SampleResponse.html");

        assertEquals("10.18", new ErcBill(serverResponse).toPay());
    }

    @Test
    public void singleFileParsedTriangulate() throws IOException {
        String serverResponse = this.loadFile("SampleResponseAnotherPayment.html");

        assertEquals("988.17", new ErcBill(serverResponse).toPay());
    }

    private String loadFile(String path) throws IOException {
        return new TextOf(new ResourceOf(path)).asString();
    }
}
