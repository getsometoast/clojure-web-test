(defproject clojure-web-test "0.0.1"
  :description "Little test project for clojure web development"
  :url "https://github.com/getsometoast/clojure-web-test.git"
  :license {:name "MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.novemberain/monger "1.7.0"]
                 [ring/ring-jetty-adapter "1.2.1"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.4"]
                 ]
  :main ^:skip-aot clojure-web-test.core
  :uberjar-name "webtest-standalone.jar"
  :profiles {:uberjar {:aot :all}}
  )
