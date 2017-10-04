(ns .chapter4)
(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})
(def vamp-data (map unify-diet-data human-consumption critter-consumption))
(println vamp-data)
(map unify-diet-data human-consumption critter-consumption)

(def mymap {:max 10 :min 5})

(def update-map (fn
                  [mmap [key value]]
                  (assoc mmap key (inc value)))
  )

(println (reduce update-map {} mymap))

(println (reduce (fn [mmap [key value]]
                   (if (> value 6)
                     (assoc mmap key value)
                     mmap
                      )
                   )
                 {} {:human 4 :critter 3})
         )

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

(println (take-while #(<= (:month %) 2) food-journal))

(println (filter #(<= (:human %) 5) food-journal))

(println (some #(= (:critter %) 2.9) food-journal))         ; if any predicate evaluates to true

(defn conjj
  [target & resr]
  (into target resr))

(defn my-into
  [target rest]
  (apply conj target rest))

(println (conjj [1] [4]))
(println (my-into [1] [4 6]))

(defn logger [log-level log-message]
  (partial log-level))

(def _even (fn [n](= (mod n 2) 0)))

(defn my-complement [fun] (fn
                            [args]
                     (not (fun args))
                     )
  )

(def odd (my-complement _even))
(println (odd 5))