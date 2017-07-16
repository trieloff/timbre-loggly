(defproject trieloff/timbre-loggly "0.1.0"
  :dependencies [[org.clojure/clojurescript "1.9.521" :exclusions [org.apache.ant/ant]]
                 [funcool/httpurr "1.0.0"]
                 [com.taoensso/timbre "4.10.0"]
                 [org.clojure/clojure "1.8.0"]]
  :author "Lars Trieloff <https://github.com/trieloff>"
  :description "A timbre (ClojureScript/Node.js) appender that posts to Loggly"
  :url "https://github.com/trieloff/timbre-loggly"
  :license {:name "Apache License 2.0"
            :url  "http://www.eclipse.org/legal/epl-v10.htmlhttps://www.apache.org/licenses/LICENSE-2.0"
            :distribution :repo}
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
