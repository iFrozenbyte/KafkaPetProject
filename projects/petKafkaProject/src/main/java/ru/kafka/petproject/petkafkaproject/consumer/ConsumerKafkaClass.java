package ru.kafka.petproject.petkafkaproject.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ConsumerKafkaClass {
    final static Logger log = LoggerFactory.getLogger(ConsumerKafkaClass.class);
    private volatile boolean keepConsuming = true;

    public static void main(String[] args) {
        Properties kaProperties = initialServerProperties();
        ConsumerKafkaClass consumerKafkaClass = new ConsumerKafkaClass();
        consumerKafkaClass.consume(kaProperties);
        Runtime.getRuntime().addShutdownHook(new Thread(consumerKafkaClass::shutdown));
    }

    private static Properties initialServerProperties() {
        Properties kaProperties = new Properties();
        kaProperties.put("bootstrap.servers", "localhost:9092,localhost:9093,localhost:9094");
        kaProperties.put("group.id", "testconsumer");
        kaProperties.put("enable.auto.commit", "true");
        kaProperties.put("auto.commit.interval.ms", "1000");
        kaProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kaProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return kaProperties;
    }

    private void consume(Properties kaProperties) {
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(kaProperties)) {
            consumer.subscribe(List.of("topic_two"));
            while (keepConsuming) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(250));
                for (ConsumerRecord<String, String> record : records) {
                    log.info("kinaction_info offset = {}, kinaction_value = {}", record.offset(), record.value());
                }
            }
        }
    }

    private void shutdown() {
        keepConsuming = false;

    }
}