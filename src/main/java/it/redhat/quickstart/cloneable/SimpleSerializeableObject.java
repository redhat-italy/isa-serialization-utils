package it.redhat.quickstart.cloneable;

import it.redhat.quickstart.common.AbstractBaseObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleSerializeableObject extends AbstractBaseObject {

    private static final long serialVersionUID = 13123L;

    @XmlElement
    private String data;

    @XmlElement
    private NestedSimpleObject nestedSimple;

    @XmlElement
    private NestedCloneableObject nestedCloneable;

    public SimpleSerializeableObject() {
    }

    public SimpleSerializeableObject(String data) {
        this.data = data;
        this.nestedSimple = new NestedSimpleObject(data);
        this.nestedCloneable = new NestedCloneableObject(data);
    }

    @Override
    public String toString() {
        return "SimpleSerializeableObject{" +
                "data='" + data + '\'' +
                ", nestedSimple=" + nestedSimple +
                ", nestedCloneable=" + nestedCloneable +
                '}';
    }

    public String getData() {
        return data;
    }
}
