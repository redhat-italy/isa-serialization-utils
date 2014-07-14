package it.redhat.quickstart.cloneable;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SimpleWebCall {

    public SimpleWebCall() {
    }

    @WebMethod
    public SimpleSerializeableObject echo(String message) {
        try {
            return (SimpleSerializeableObject) (new SimpleSerializeableObject(message)).clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
