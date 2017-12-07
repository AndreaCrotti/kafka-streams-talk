(ns kafka-streams-talk.core-test
  (:require [clojure.test :refer :all]
            [kafka-streams-talk.core :as sut]))

(deftest reconciliation-test
  (testing "reconciles"
    (are [transaction repayment reconciles?] (= reconciles? (sut/reconciles? transaction repayment))

      {:amount-cents 100 :value-date "2018-01-01"}
      {:amount-cents 100 :effective-date "2018-01-01"}
      true

      {:amount-cents 100 :value-date "2018-01-01"}
      {:amount-cents 102 :effective-date "2018-01-01"}
      false

      {:amount-cents 100 :value-date "2017-12-01"}
      {:amount-cents 100 :effective-date "2018-01-01"}
      false)))
