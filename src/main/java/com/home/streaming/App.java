package com.home.streaming;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.Properties;

public class App {

  static final String DONE = "done";
  private static final Logger log = Logger.getLogger(App.class);

  public static void main(String[] args) {
    log.warn("!!!!!!!!!!!!!!!!!!!Starting CONSUMER");
    Properties config = new Properties();
    config.put(StreamsConfig.APPLICATION_ID_CONFIG, "ks-scaling-app-app-id");
    config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,
      Optional.ofNullable(System.getenv("BOOTSTRAP_SERVERS_CONFIG")).orElse("localhost:9094")
    );
    config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
    config.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 100);
    config.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);

    StreamsBuilder builder = new StreamsBuilder();

    builder
      .stream("inScalingTopic", Consumed.with(Serdes.String(), Serdes.String()))
      .peek((key, value) -> {
        if(!DONE.equals(value)) {
          try {
            Thread.sleep(10);
            if (Long.valueOf(value.substring(3)) % 1000 == 0) {
              log.info("Processed another 1000 messages");
            }
          } catch (InterruptedException e) {
            log.error(e);
          }
        }
      })
      .filter((key, value) -> DONE.equals(value))
      .peek((key, value) -> log.info("Done with key " + key))
      .to("outScalingTopic", Produced.with(Serdes.String(), Serdes.String()))
    ;

    KafkaStreams streams = new KafkaStreams(builder.build(), config);
    streams.start();

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        streams.close();
        log.info("Stream stopped");
      } catch (Exception exc) {
        log.error("Got exception while executing shutdown hook: ", exc);
      }
    }));
  }
}

