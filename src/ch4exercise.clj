(ns .ch4Problem)
(def filename "resources/suspects.csv")
(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversion {:name identity
                 :glitter-index str->int})

(defn convert
  [vamp-key v]
  ((get conversion vamp-key) v)
  )

(defn parse
  [buffer]
  (map #(clojure.string/split % #",") (clojure.string/split buffer #"\n")
       )
  )
(defn mapify
  [_list]
  (map (fn [unmapped-row]
         (reduce (fn [new-map [key value]]
                   (assoc new-map key (convert key value))
                   ) {} (map vector vamp-keys unmapped-row))
         )
       _list)
  )

(defn glitter-filter [mininum-glitter records]
  (filter #(>= (get % :glitter-index) mininum-glitter )
          records)
  )
(def all-suspects (mapify (parse (slurp filename))))

(println (glitter-filter 5 (mapify (parse (slurp filename)))))

;#Problem 1
(defn get-names [record-map]
  (map #(:name %) record-map)
  )
(println (get-names all-suspects))

;#Problem 2
(defn append
  ([suspect records]
          (into records (mapify [suspect])))
  )
(println (append ["kim" "99"] all-suspects))

;#Problem 3
(defn is-present [x]
  (empty x)
  )
(def validation {
                 :name is-present
                 :glitter-index is-present
                 })

(defn validate [valid-keys vv]
  (reduce (fn [first second] (and first second))
          true
          (map #(not (empty? (% vv)))
               (keys valid-keys)
               )
          )
  )
(println (validate validation {:name "hk"}))

;#Problem 4
(defn csv-helper [value]
  (str (str (:name value) ",") (:glitter-index value))
  )
(defn list-to-csv [_list]
  (clojure.string/join "\n"
    (map csv-helper _list))
  )
(println (list-to-csv all-suspects))