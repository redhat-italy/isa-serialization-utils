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

    public byte[] marshall(Object object) {
        ByteArrayOutputStream myArray = new ByteArrayOutputStream();

        RiverMarshallerFactory factory = new RiverMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        Marshaller marshaller = null;

        // Enable Modular Serialization!
        configuration.setClassResolver(ModularClassResolver.getInstance(ModuleLoader.forClassLoader(object.getClass().getClassLoader())));

        try {
            // Create a marshaller on some stream we have
            marshaller = factory.createMarshaller(configuration);
            marshaller.start(new OutputStreamByteOutput(myArray));

            // Write lots of stuff
            marshaller.writeObject(object);
            marshaller.flush();

            return myArray.toByteArray();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } finally {
            // Done
            if (marshaller != null) {
                try {
                    marshaller.finish();
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }

    public Object unmarshall(Class clazz, byte[] bytes) {
        ByteArrayOutputStream myArray = new ByteArrayOutputStream();

        RiverMarshallerFactory factory = new RiverMarshallerFactory();
        MarshallingConfiguration configuration = new MarshallingConfiguration();
        Unmarshaller unmarshaller = null;

        // Enable Modular Serialization!
        configuration.setClassResolver(ModularClassResolver.getInstance(ModuleLoader.forClassLoader(clazz.getClassLoader())));

        try {
            // Create a unmarshaller on some stream we have
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
            // Done
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
