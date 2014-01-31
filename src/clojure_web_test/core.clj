(ns clojure-web-test.core
  (:require [compojure.core :as compojure]
            [ring.adapter.jetty :as ring]
            [monger.core :as mg]
            [clj-statsd :as statsd]
            [monger.collection :as collection]
            [clojure.data.json :as json])
  (:use [monger.core :only [connect! connect set-db! get-db]])
  (:import [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]))

(connect!)
(set-db! 
  (monger.core/get-db "monger-test"))
(statsd/setup "localhost" 8125)

(statsd/gauge :test_thing 50)

(compojure/defroutes routes 
  (compojure/GET "/user/:id/locker/:trackid" [id trackid] (json/write-str (collection/find-one-as-map "documents" { :userId id :trackId trackid })))
  (compojure/POST "/user/:id/locker" {body :body} (collection/insert-and-return "documents" (json/read-str (slurp body)))))

(defn -main []
    (ring/run-jetty #'routes {:port 8881 :join? false}))
