(ns clojure-design-patterns.core
  (:gen-class))

;; Accessing the history of a `ref `in Clojure

(def change (ref "hello" :min-history 10))

(def history-of-change (atom [@change]))

(add-watch change :watcher (fn [_ _ old-value new-value]
                                (when (not= old-value new-value)
                                  (swap! history-of-change conj new-value))))

(do
  (dosync (ref-set change "better"))
  @change)

(let [exclamator (fn [x] (str x "!"))]
  (dosync
   (alter change exclamator)
   (alter change exclamator)
   (alter change exclamator))
  @change)

(ref-history-count change)