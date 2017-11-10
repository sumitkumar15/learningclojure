
(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))
(defn validate
  "Returns a map with a vector of errors for each key"
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

;(validate order-details order-details-validations)
(defmacro if-valid
  "Handle validation more concisely"
  [to-validate validations errors-name & then-else]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (if (empty? ~errors-name)
       ~@then-else)))
(if-valid order-details order-details-validations errors
          (render :success)
          (render :failure errors))
;Problem 1
(defmacro when-valid
  [to-validate validations errors-name & then-else]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (when (empty? ~errors-name)
       ~@then-else)))
;problem 2
(defmacro orr
  ([] nil)
  ([x] x)
  ([x & more]
    `(let [orr# ~x]
       (if orr# orr# (orr ~@more)))))

;Problem 3
;(defattrs c-int :intelligence
;          c-str :strength
;          c-dex :dexterity)
(defmacro defattrs
  ([] nil)
  ([fn-name attr]
   `(def ~fn-name (comp ~attr :attributes)))
  ([fn-name attr & rest]
   `(do
      (defattrs ~fn-name ~attr)
      (defattrs ~@rest))
    )
  )