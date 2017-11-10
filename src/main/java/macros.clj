(ns .macros)
(defmacro infix
  [inlist]
  (list (second inlist) (first inlist) (last inlist))
  )
(defn infix-eval
  [s-list remain]
  (if (nil? remain)
    )
  )
(println (infix (3 + 4)))
(infix-eval '() '((1 + (3 * 4)) - 5))