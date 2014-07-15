package it.redhat.quickstart.common;

import org.jboss.marshalling.*;
import org.jboss.marshalling.river.RiverMarshallerFactory;
import org.jboss.modules.ModuleLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class JBossMarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractBaseObject.class);

    public static byte[] marshall(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Cannot marshall a null object");
        }

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        RiverMarshallerFactory factory = new RiverMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();

        configuration.setClassResolver(ModularClassResolver.getInstance(ModuleLoader.forClass(object.getClass())));

        Marshaller marshaller = null;
        try {
            marshaller = factory.createMarshaller(configuration);
            marshaller.start(new OutputStreamByteOutput(bytes));

            marshaller.writeObject(object);
            marshaller.flush();

            return bytes.toByteArray();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (marshaller != null) {
                try {
                    marshaller.finish();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public static Object unmarshall(Class clazz, byte[] bytes) {
        RiverMarshallerFactory factory = new RiverMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();

        configuration.setClassResolver(ModularClassResolver.getInstance(ModuleLoader.forClass(clazz)));

        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = factory.createUnmarshaller(configuration);
            unmarshaller.start(new InputStreamByteInput(new ByteArrayInputStream(bytes)));

            return unmarshaller.readObject();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            if (unmarshaller != null) {
                try {
                    unmarshaller.finish();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
