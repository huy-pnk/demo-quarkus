package org.test.messaging;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class MessageProducer {

    @Inject
    @Channel("products-out")
    Emitter<String> productsOut;

    public void send(String message) {
        productsOut.send(message);
    }
}
