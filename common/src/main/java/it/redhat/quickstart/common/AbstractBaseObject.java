package it.redhat.quickstart.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public abstract class AbstractBaseObject implements Cloneable, Serializable {

    private static final String CLASS_NAME = "org.jboss.marshalling.river.RiverMarshallerFactory";
    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseObject.class);
    private static final long serialVersionUID = 1L;

    @Override
    public Object clone() throws CloneNotSupportedException {
        if(AbstractBaseObject.useJBoss()) {
            return JBossMarshaller.unmarshall(this.getClass(), JBossMarshaller.marshall(this));
        } else {
            return deserialize(this.serialize());
        }
    }


    public byte[] serialize() {
        ByteArrayOutputStream myArray = null;
        ObjectOutputStream obj = null;
        try {
            myArray = new ByteArrayOutputStream();
            obj = new ObjectOutputStream(myArray);
            obj.writeObject(this);
            return myArray.toByteArray();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (obj != null) {
                try {
                    obj.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (myArray != null) {
                try {
                    myArray.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public static AbstractBaseObject deserialize(byte[] bytes) {
        try {
            ByteArrayInputStream myArray = new ByteArrayInputStream(bytes);
            ObjectInputStream obj = new ObjectInputStream(myArray);
            return (AbstractBaseObject) obj.readObject();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static boolean useJBoss() {
        try {
            return (Class.forName(AbstractBaseObject.CLASS_NAME) != null);
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
