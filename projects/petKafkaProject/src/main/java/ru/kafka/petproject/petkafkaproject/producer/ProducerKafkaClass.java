package ru.kafka.petproject.petkafkaproject.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import ru.kafka.petproject.petkafkaproject.source.SourceClass;

import java.io.IOException;
import java.util.Properties;

public class ProducerKafkaClass {
    public static void main(String[] args) {
        Properties kaProperties = initialProperties();
        try (Producer<String, String> producer = new KafkaProducer<>(kaProperties)) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test_topic", null, SourceClass.getMessagesFromTopic());
            producer.send(producerRecord);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Properties initialProperties() {
        Properties kaProperties = new Properties();
        kaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        kaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return kaProperties;
    }
}