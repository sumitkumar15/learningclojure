(ns .chapter5)
(defn sum
  ([vals] (sum vals 0))
  ([vals accumulator]
    (if (empty? vals)
      accumulator
      (sum (rest vals) (+ accumulator (first vals)))
      )
    )
  )

(println (sum [1 2 3 4 5]))

(defn sum2
  ([vals] (sum vals 0))
  ([vals accumulator]
   (if (empty? vals)
     accumulator
     (recur (rest vals) (+ accumulator (first vals)))
     )
    )
  )
(println (sum2 [1 2 3 4 5]))

; composing functions
(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}}
  )
(def get-attr-int (comp :intelligence :attributes))
(def get-attr-str (comp :strength :attributes))
(def get-attr-dex (comp :dexterity :attributes))

(println (get-attr-dex character))
(defn comp-2 [a b]
  (fn [& args]
    (a (apply b args)))
  )

(println ((comp-2 :dexterity :attributes) character))

(defn comp-many
  [& f-list]
    (if (empty? f-list)
      identity
      (reduce (fn [a b] (comp-2 a b)) identity f-list)
      )
  )

(println ((comp-many inc +) 2 6 7))

(defn test-memo [x]
  (Thread/sleep 5000)
  "hello"
  )
(def test (memoize (test-memo 5)))
(println test)
(println test)
;(def mylist [2 4 6 8 10])

