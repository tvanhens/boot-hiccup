(set-env!
 :source-paths #{"src"}
 :dependencies
 '[[org.clojure/clojure "1.6.0"  :scope "provided"]

   [adzerk/bootlaces    "0.1.11" :scope "test"]
   [hiccup              "1.0.5"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.0.1")

(bootlaces! +version+)
