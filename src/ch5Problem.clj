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
