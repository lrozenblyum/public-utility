package com.leokom.publicutility.bill;

/**
 * Bill for public utility service.
 * @author Leonid Rozenblium (lrozenblyum@gmail.com)
 * @since 0.0.1
 */
public interface Bill {

    /**
     * Tell how many we need to pay for the service.
     * 
     * @return Amount of money to pay
     */
    String toPay();

}
