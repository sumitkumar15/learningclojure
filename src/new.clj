(ns .new)
(defn add [x y]
  (+ x y))
(println (add 2 3))
(if true
  (println "Hello World"))

(when nil
  (println "on empty"))
(println (or 1 (or 0 0)))

(def test-function
  ["1" "2" "3"])
(println test-function)

(def test-function1
  [1 2 3])
(println test-function1)

(defn test-argument
  [arg1 arg2]
  (if (= arg1 1)
    (println "arg1 is 1"))
  (println arg2))
(test-argument 1 500)
(println (str "con" "cat"))

(println (get {
               :first-name "sk"
               :last-name "batman"
               } :first-name))

(println (get [1 2 3 4 5] 4))

(println (conj [9 8 7] 3))

(println '(1 0 0 2))

(println (nth '(3 5 7) 2))

(println (set '(3 5 5 6 6)))

(def hset #{3 5 7 8})

(println (contains? hset 7))

(println (* (+ 1 2) 2))

(println ((and (= 1 1) +) 3 4 5))

(println (map inc [1 2 3 4]))

(defn add-num
  "adds two numbers"
  [a b]
  (+ a b))
(println add-num 3 4)

(defn param-test
  [& listparam]
  (println listparam))

(param-test "hello" "world" "is" "old")

(println (#(* % 4) 9) )

(println (let [x 40] x))

(let [[head & tail] [9 8 7 6 1]]
  (println head tail))

(loop [iter 0]
  (println iter)
  (if (< iter 5)
    (recur (inc iter))))

(loop [iteration 0]
  (println (str "Iteration " iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))


(defn inc-maker
  [inc-by]
  #(+ % inc-by))
(def inc5 (inc-maker 5))
(println (inc5 19))


