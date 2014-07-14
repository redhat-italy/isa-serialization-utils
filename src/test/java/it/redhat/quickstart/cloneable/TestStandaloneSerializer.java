package it.redhat.quickstart.cloneable;


import org.junit.Assert;
import org.junit.Test;

public class TestStandaloneSerializer {

    @Test
    public void testSerializer() throws CloneNotSupportedException {
        String expected = "Message";
        SimpleSerializeableObject message = new SimpleSerializeableObject(expected);

        SimpleSerializeableObject clone = (SimpleSerializeableObject) message.clone();

        Assert.assertEquals(expected, clone.getData());
    }
}
