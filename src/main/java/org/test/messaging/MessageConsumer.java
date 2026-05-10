package org.test.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MessageConsumer {

    private static final Logger LOG = Logger.getLogger(MessageConsumer.class);

    @Incoming("products-in")
    public void consume(String message) {
        LOG.infof("Received message from Kafka: %s", message);
    }
}
