(ns timbre.loggly
  (:require [httpurr.client :as http]
            [taoensso.timbre :as timbre
             :refer-macros [log trace debug info warn error fatal report
                            logf tracef debugf infof warnf errorf fatalf reportf
                            spy get-env]]
            [httpurr.client.node :refer [client]]
            [clojure.string :as string]
            [taoensso.encore :as enc]
            [cljs.nodejs :as nodejs]))

(defn not-string? [x]
  (not (string? x)))

(defn first-or-all [x]
  (if (= (count x) 1) (first x) x))

(defn loggly-appender [& [opts]]
  (let [{:keys [token tags]} opts]
    {:enabled?   true
     :async?     true
     :rate-limit [[1 (enc/ms :secs 1)]]
     :output-fn  #(do
                    (.stringify js/JSON (clj->js {:args      (first-or-all (filter not-string? (:vargs %)))
                                                  :level     (:level %)
                                                  :namespace (:?ns-str %)
                                                  :message   (apply str (filter string? (:vargs %)))
                                                  :line      (:?line %)})))
     :fn         (fn [data]
                   (let [{:keys [output_]} data
                         tag (string/join "," (map name (if (nil? tags) '[:timbre] tags)))]
                     (http/post client
                                (str "http://logs-01.loggly.com/inputs/" token "/tag/" tag "/")
                                {:body    (force output_)
                                 :headers {"Content-Type" "application/json"}})))}))