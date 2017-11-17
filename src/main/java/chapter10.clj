;(def fred (atom {:cuddle-hunger-level 0
;                 :percent-deteriorated 0}))
;(println @fred)
;
;(swap! fred
;       (fn [current-state]
;         (merge-with + current-state {:cuddle-hunger-level 1})))
;
;(let [num (atom 1)
;      s1 @num]
;  (swap! num inc)
;  (println "State 1:" s1)
;  (println "Current state:" @num))
;
;(defn shuffle-speed
;  [zombie]
;  (* (:cuddle-hunger-level zombie)
;     (- 100 (:percent-deteriorated zombie))))
;
;(defn shuffle-alert
;  [key watched old-state new-state]
;  (let [sph (shuffle-speed new-state)]
;    (if (> sph 5000)
;      (do
;        (println "Run, you fool!")
;        (println "The zombie's SPH is now " sph)
;        (println "This message brought to your courtesy of " key))
;      (do
;        (println "All's well with " key)
;        (println "Cuddle hunger: " (:cuddle-hunger-level new-state))
;        (println "Percent deteriorated: " (:percent-deteriorated new-state))
;        (println "SPH: " sph)))))
;
;(reset! fred {:cuddle-hunger-level 22
;              :percent-deteriorated 2})
;(add-watch fred :fred-shuffle-alert shuffle-alert)
;
;(swap! fred update-in [:percent-deteriorated] + 1)
;
;(swap! fred update-in [:cuddle-hunger-level] + 30)
;
;;Validators
;(defn percent-deteriorated-validator
;  [{:keys [percent-deteriorated]}]
;  (and (>= percent-deteriorated 0)
;       (<= percent-deteriorated 100)))
;
;(def bobby
;  (atom
;    {:cuddle-hunger-level 0 :percent-deteriorated 0}
;    :validator percent-deteriorated-validator))
;;(swap! bobby update-in [:percent-deteriorated] + 200)
;
;(def sock-varieties
;  #{"darned" "argyle" "wool" "horsehair" "mulleted"
;    "passive-aggressive" "striped" "polka-dotted"
;    "athletic" "business" "power" "invisible" "gollumed"})
;
;(defn sock-count
;  [sock-variety count]
;  {:variety sock-variety
;   :count count})
;
;(defn generate-sock-gnome
;  "Create an initial sock gnome state with no socks"
;  [name]
;  {:name name
;   :socks #{}})
;
;(def sock-gnome (ref (generate-sock-gnome "Barumpharumph")))
;(def dryer (ref {:name "LG 1337"
;                 :socks (set (map #(sock-count % 2) sock-varieties))}))
;
;(println (:socks @dryer))

;(time (dorun (map inc (doall (range 10000000)))))
;;no performance improvement due to threading overhead greater than computation overhead
;(time (dorun (pmap inc (doall (range 10000000)))))
;;performance can be increased by partitioninng the data & giving more work to single threads
;(time (dorun (pmap (fn [x] (doall (map inc x))) (doall (partition-all 10000 (range 10000000))))))

;Exercises
;problem 1
(def x (atom 0))
(println @x)
(swap! x inc)
(println @x)
(swap! x inc)
(swap! x inc)
(println @x)
(println (deref x))

; problem 2
;similar but give quote count after all are de-refed
(def quotecount (atom 0))
(defn quote-word-count
  "Non parallel & blocking"
  [x]
  (loop [i x]
    (when (> i 0)
      (let [res (future (slurp "https://www.braveclojure.com/random-quote"))]
        (swap! quotecount (fn [x] @res (inc @quotecount))))
      (recur (dec i)))
    )
  @quotecount)
(time (quote-word-count 10))

(defn quote-word-count2
  "running on parallel threads"
  [x]
  (let [result (pmap
                 (fn [x] (future (slurp "https://www.braveclojure.com/random-quote")))
                 (range x))
        ]
    (map #(deref %) result)))
(time (dorun (quote-word-count2 10)))

;problem 3

