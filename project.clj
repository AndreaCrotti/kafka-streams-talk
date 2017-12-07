(defproject kafka-streams-talk "0.1.0-SNAPSHOT"
  :description "Talk about Kafka and kafka streams"
  :url "https://github.com/AndreaCrotti/kafka-streams-talk"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.apache.kafka/kafka "0.7.2"]
                 ;; which one should we use?
                 ;; [org.apache.kafka/kafka_2.11 "0.11.0.1"]
                 [org.apache.kafka/kafka-streams "1.0.0"]
                 [org.apache.kafka/kafka-clients "1.0.0"]
                 ;; what could be another useful thing to check?
                 [clj-http "3.7.0"]
                 [clj-time "0.14.2"]
                 [org.clojure/spec.alpha "0.1.143"]
                 [org.slf4j/slf4j-log4j12 "1.7.25"]
                 [org.clojure/tools.logging "0.3.1"]
                 [environ "1.1.0"]
                 [environ "1.1.0"]]

  :repositories [["confluent" {:url "https://packages.confluent.io/maven/"}]]
  :main kafka-streams-talk.word-count

  :plugins [[lein-docker-compose "0.1.0"]])
