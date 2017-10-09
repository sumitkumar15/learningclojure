(ns .ch5Problem)
(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}}
  )
(def get-attr-int (comp :intelligence :attributes))
(def get-attr-str (comp :strength :attributes))
(def get-attr-dex (comp :dexterity :attributes))
;Problem 1
(defn attr [prop]
  (prop (:attributes character))
  )
(println (attr :strength))
; Problem 2
(defn comp-2 [a b]
  (fn [& args]
    (a (apply b args)))
  )

(defn comp-2 [a b]
  (fn [& args]
    (a (apply b args)))
  )

(defn comp-many
  [& f-list]
  (if (empty? f-list)
    identity
    (reduce (fn [a b] (comp-2 a b)) identity f-list)
    )
  )

;Problem 3
(defn my-assoc-in [dest [k & ks] target]
  (if (= ks nil)
    (assoc dest k target)
    (assoc dest k (my-assoc-in (k dest) ks target))
    )
  )
(println (my-assoc-in character [:attributes :speed] 99))

;Problem 4 & 5
(defn my-update-in [dest [k & ks] func value]
  (if (= ks nil)
    (assoc dest k (func (get dest k) value))
    (assoc dest k (my-update-in (k dest) ks func value))
    )
  )
(println (my-update-in character [:attributes :strength] + 4))
