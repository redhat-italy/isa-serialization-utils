package it.redhat.quickstart.cloneable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NestedCloneableObject implements Cloneable, Serializable{

    private static final long serialVersionUID = 1132L;

    @XmlElement
    private String data;

    public NestedCloneableObject(String data) {
        this.data = data;
    }

    public NestedCloneableObject() {
    }

    @Override
    public String toString() {
        return "NestedCloneableObject{" +
                "data='" + data + '\'' +
                '}';
    }
}
