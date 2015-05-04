(ns boot-hiccup.core
  {:boot/export-tasks true}
  (:require [boot.core       :as core]
            [clojure.java.io :as io]
            [clojure.string  :as str]
            [hiccup.core     :as hiccup]
            [hiccup.page     :as page]))

(defn hiccup-ext->html-ext
  [path]
  (str/replace path #".hiccup.edn$" ".html"))

(defn compile-template!
  [in-file out-file]
  (doto out-file
    io/make-parents
    (spit (->> in-file
               slurp
               load-string
               hiccup/html
               ;; Should parametrize the page doctype and pass in as argument
               ;; from the boot task
               (str (:html5 page/doctype))))))

(core/deftask hiccup
  "Generates an HTML5 file for every *.hiccup.html input file."
  []
  (let [tmp (core/temp-dir!)]
    (core/with-pre-wrap fileset
      (core/empty-dir! tmp)
      (let [in-files (core/input-files fileset)
            hiccup-templates (core/by-ext [".hiccup.edn"] in-files)]
        (doseq [in hiccup-templates]
          (let [in-file  (core/tmpfile in)
                in-path  (core/tmppath in)
                out-path (hiccup-ext->html-ext in-path)
                out-file (io/file tmp out-path)]
            (compile-template! in-file out-file)))
        (-> fileset
            (core/add-resource tmp)
            core/commit!)))))
