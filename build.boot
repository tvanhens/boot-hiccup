(set-env!
 :source-paths #{"src"}
 :dependencies
 '[[org.clojure/clojure "1.6.0"  :scope "provided"]

   [adzerk/bootlaces    "0.1.11" :scope "test"]
   [hiccup              "1.0.5"]])

(require '[adzerk.bootlaces :refer :all])

(def +version+ "0.0.1")

(bootlaces! +version+)

(task-options!
 pom {:project     'boot-hiccup
      :version     +version+
      :description "Boot task to compile *.hiccup.edn files to HTML."
      :url         "https://github.com/tvanhens/boot-hiccup"
      :scm         {:url "https://github.com/tvanhens/boot-hiccup"}
      :license     {"EPL" "http://www.eclipse.org/legal/epl-v10.html"}})
