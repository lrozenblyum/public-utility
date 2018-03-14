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
