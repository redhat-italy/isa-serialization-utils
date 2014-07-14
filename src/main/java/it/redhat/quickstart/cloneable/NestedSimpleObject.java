package it.redhat.quickstart.cloneable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NestedSimpleObject implements Serializable {

    private static final long serialVersionUID = 13123L;

    @XmlElement
    private String data;

    public NestedSimpleObject(String data) {
        this.data = data;
    }

    public NestedSimpleObject() {
    }

    @Override
    public String toString() {
        return "NestedSimpleObject{" +
                "data='" + data + '\'' +
                '}';
    }
}
