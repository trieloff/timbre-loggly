(defproject timbre-loggly "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojurescript "1.9.521" :exclusions [org.apache.ant/ant]]
                 [funcool/httpurr "1.0.0"]
                 [com.taoensso/timbre "4.10.0"]
                 [org.clojure/clojure "1.8.0"]]
  :plugins [[lein-cljsbuild "1.1.6"]
            [lein-doo "0.1.7"]]
  :clean-targets ^{:protect false} ["target"]
  :cljsbuild {
    :builds [{:id "prod"
              :source-paths ["src/cljs"]
              :compiler {:main timbre.loggly
                         :output-to "target/timbre-loggly.js"
                         :language-in  :ecmascript5
                         :language-out :ecmascript5
                         :pretty-print true
                         :closure-output-charset "US-ASCII" ;; USA! USA! USA! https://github.com/google/closure-compiler/issues/1704
                         :hashbang false
                         :optimizations :simple ;; notice this!
                         :target :nodejs }}]})
