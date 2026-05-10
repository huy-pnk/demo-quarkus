package org.test.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logging.Logger;

@ApplicationScoped
public class MessageConsumer {

    private static final Logger LOG = Logger.getLogger(MessageConsumer.class);

    @Incoming("products-in")
    public void consume(ConsumerRecord<String, String> record) {
        LOG.infof("Received message from Kafka: topic=%s partition=%d offset=%d value=%s",
                record.topic(), record.partition(), record.offset(), record.value());
    }
}
