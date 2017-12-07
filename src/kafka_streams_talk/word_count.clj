(ns kafka-streams-talk.word-count
  (:require [clojure.tools.logging :as log]
            [environ.core :refer [env]])
  (:import (org.apache.kafka.streams.kstream KStreamBuilder ValueMapper)
           (org.apache.kafka.streams KafkaStreams StreamsConfig)
           (org.apache.kafka.common.serialization Serdes))
  (:gen-class))

(def topic-names
  {:input "words"
   :output "words-count"})

(def props
  {StreamsConfig/APPLICATION_ID_CONFIG    "words-count"
   StreamsConfig/BOOTSTRAP_SERVERS_CONFIG "localhost:9092"
   StreamsConfig/KEY_SERDE_CLASS_CONFIG   (.getName (.getClass (Serdes/String)))
   StreamsConfig/VALUE_SERDE_CLASS_CONFIG (.getName (.getClass (Serdes/String)))})

(defn word-count
  [words]
  (log/info "Checking words " words)
  ((comp str count) words))

(def builder
  (KStreamBuilder.))

(->
 ;; transform the input topic into a stream
 (.stream builder (into-array String [(:input topic-names)]))
 ;; map chars count on the values
 (.mapValues (reify ValueMapper
               (apply [_ v] (word-count v))))
 ;; finally write it out to the result topic
 (.to (:output topic-names)))

(def config (StreamsConfig. props))
(def streams (KafkaStreams. builder config))

(defn -main [& args]
  (log/infof "Starting the word count processor, reading from *%s* and writing to *%s*"
             (:input topic-names)
             (:output topic-names))

  (.start streams)
  (Thread/sleep (* 60000 10))
  (log/info "Stopping the word count processor"))
