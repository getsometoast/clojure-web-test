(ns clojure-web-test.core
  (:require [compojure.core :refer [defroutes GET]]
            [ring.adapter.jetty :as ring]
            [monger.core :as mg]
            [monger.collection :as mc]
            [clojure.data.json :as json]
  )
  (:use [monger.core :only [connect! connect set-db! get-db]]
        [monger.collection :only [insert insert-batch]]
  )
  (:import [org.bson.types ObjectId]
           [com.mongodb DB WriteConcern]
  )
)


(connect!)
(set-db! (monger.core/get-db "monger-test"))

(defroutes routes
  (GET "/insert/:name/:id" [name id] (insert "documents" { :_id id :first_name name :last_name "Lennon" }))
  (GET "/get/:id" [id] (json/write-str (mc/find-one-as-map "documents" { :_id id })))
)

(defn -main []
    (ring/run-jetty #'routes {:port 8881 :join? false}))
