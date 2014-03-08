(ns cascalog-for-dummies.dummy-1
  (:use [clojure.test]
        [cascalog api]
        [midje sweet cascalog]))

(fact "All"
      (<- [?a]
          ([[1]] ?a)) =>
      (produces [[1]]))

(fact "Filter"
      (<- [?a]
          ([[1]
            [2]] ?a)
          (< ?a 2)) =>
      (produces [[1]]))

(fact "Join"
      (<- [?a ?c]
          ([[1 2]] ?a ?b)
          ([[2 3]] ?b ?c)) =>
      (produces [[1 3]]))

;Next examples are inspired, when not copied, from
;http://jimdrannbauer.com/2011/02/04/cascalog-made-easier/
(defmapfn my-name-is
          [name]
          ["my" "name" "is"])

(fact "Expands"
      (<- [?my ?name ?is ?my-name]
          ([["Jim"]
            ["Kerry"]] ?my-name)
          (my-name-is ?my-name :> ?my ?name ?is)) =>
      (produces [["my" "name" "is" "Jim"]
                 ["my" "name" "is" "Kerry"]]))

(defmapcatfn explode-single
             [bs]
             (map vector bs))

(fact "Explodes - single column"
      (<- [?a ?b]
          ([["this" ["should" "be" "vertical"]]] ?a ?bs)
          (explode-single ?bs :> ?b)) =>
      (produces [["this" "should"]
                 ["this" "be"]
                 ["this" "vertical"]]))

(defmapcatfn explode-multi
             [a b c d]
             [[a] [b] [c] [d]])

(fact "Explodes - multi column"
      (<- [?e]
          ([["this" "should" "be" "vertical"]] ?a ?b ?c ?d)
          (explode-multi ?a ?b ?c ?d :> ?e)) =>
      (produces [["this"]
                 ["should"]
                 ["be"]
                 ["vertical"]]))

(defaggregatefn implode-single
                ([] [])
                ([word-list] [[word-list]])
                ([word-list word] (conj word-list word)))

(fact "Implodes - single column"
      (<- [?a ?c]
          ([["this" "should"]
            ["this" "be"]
            ["this" "horizontal"]] ?a ?b)
          (implode-single ?b :> ?c)) =>
      (produces [["this" ["should" "be" "horizontal"]]]))


(defaggregatefn implode-single
                ([] [])
                ([word-list] [word-list])
                ([word-list word] (conj word-list word)))

(fact "Implodes - multi column"
      (<- [?a ?b ?c ?d]
          ([["this"]
            ["should"]
            ["be"]
            ["horizontal"]] ?w)
          (implode-single ?w :> ?a ?b ?c ?d)) =>
      (produces [["this" "should" "be" "horizontal"]]))








