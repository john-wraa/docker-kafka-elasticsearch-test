package no.responseweb.kafka.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoKeys {
    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);

        String bootstrapServers = "localhost:9092";

        // Create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10; i++) {

            String topic = "first_topic";
            String value = "Hello World! " + i;
            String key = "id_" + i;

            // Create a Producer Record
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

            logger.info("Key: {}", key);

            // Send data
            producer.send(record, (recordMetadata, e) -> {
                // execute every time a record is successfully sent or an exception is thrown
                if (e == null) {
                    // the record was successfully sent
                    logger.info(
                            "Received new metadata: \n" +
                                    "  Topic: {} \n" +
                                    "  Partition: {} \n" +
                                    "  Offset: {} \n" +
                                    "  Timestamp: {} \n"
                            , recordMetadata.topic()
                            , recordMetadata.partition()
                            , recordMetadata.offset()
                            , recordMetadata.timestamp()
                    );

                } else {
                    // an exception is thrown
                    logger.error("Error while producing: ", e);
                }
            });

        }

        // Flush data
        producer.flush();
        producer.close();

    }
}
