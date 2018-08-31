(set-env!
  :source-paths #{"src"}
  :dependencies '[[adzerk/boot-cljs "2.1.4" :scope "test"]
                  [adzerk/boot-reload "0.5.2" :scope "test"]
                  [org.clojure/test.check "0.9.0" :scope "test"]
                  [nightlight "RELEASE" :scope "test"]
                  ; project deps
                  [org.clojure/clojure "1.9.0"]
                  [org.clojure/clojurescript "1.10.238"]
                  [org.clojure/core.async "0.3.443"]
                  [org.clojure/data.json "0.2.6"]
                  [org.clojure/tools.cli "0.3.5"]
                  [javax.xml.bind/jaxb-api "2.3.0"]
                  [http-kit "2.2.0"]
                  [ring "1.6.3"]
                  [reagent "0.8.0-alpha2"]
                  [cljs-react-material-ui "0.2.48"]
                  [rum "0.11.2"]
                  [play-cljs "1.2.0"]
                  [bidi "2.1.3"]
                  [com.andrewmcveigh/cljs-time "0.5.2"]
                  [com.rpl/specter "1.0.4"]
                  [com.taoensso/sente "1.11.0"]
                  [org.clojure/java.jdbc "0.7.3"]
                  [com.h2database/h2 "1.4.196"]
                  [honeysql "0.9.1"]
                  [edna "1.1.0"]])

(task-options!
  pom {:project 'ticketcounter
       :version "1.0.0-SNAPSHOT"
       :description "FIXME: write description"}
  aot {:namespace '#{ticketcounter.server}}
  jar {:main 'ticketcounter.server}
  sift {:include #{#"\.jar$"}})

(require
  '[clojure.spec.test.alpha :refer [instrument]]
  '[nightlight.core :as nightlight]
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-reload :refer [reload]]
  'ticketcounter.server)

(deftask run []
  (set-env! :resource-paths #{"resources" "dev-resources"})
  (comp
    (with-pass-thru _
      (instrument)
      (let [server (ticketcounter.server/-main)
            port (-> server meta :local-port)
            url (str "http://localhost:" port "/index.html")]
        (println "Started app on" url)
        (nightlight/start {:port 4000 :url url})))
    (watch)
    (reload :asset-path "ticketcounter")
    (cljs
      :source-map true
      :optimizations :none
      :compiler-options {:asset-path "main.out"})
    (target)))

(deftask build []
  (set-env! :resource-paths #{"resources" "prod-resources"})
  (comp
    (cljs :optimizations :advanced)
    (aot)
    (pom)
    (uber)
    (jar)
    (sift)
    (target)))
