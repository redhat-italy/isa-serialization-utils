package it.redhat.quickstart.cloneable;

import org.apache.commons.lang.SerializationUtils;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class SimpleWebCall {

    public SimpleWebCall() {
    }

    @WebMethod
    public SimpleSerializeableObject echoCopy(String message) {
        return (SimpleSerializeableObject) SerializationUtils.clone(new SimpleSerializeableObject(message));
    }
}
