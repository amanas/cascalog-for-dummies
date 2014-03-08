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

(defmapfn my-name-is
          [name]
          ["my" "name" "is"])

(fact "Expands - http://jimdrannbauer.com/2011/02/04/cascalog-made-easier/"
      (<- [?my ?name ?is ?my-name]
          ([["Jim"]
            ["Kerry"]] ?my-name)
          (my-name-is ?my-name :> ?my ?name ?is)) =>
      (produces [["my" "name" "is" "Jim"]
                 ["my" "name" "is" "Kerry"]]))

(defmapcatfn explode
             [bs]
             (map vector bs))

(fact "Explodes - http://jimdrannbauer.com/2011/02/04/cascalog-made-easier/"
      (<- [?a ?b]
          ([["this" ["should" "be" "vertical"]]] ?a ?bs)
          (explode ?bs :> ?b)) =>
      (produces [["this" "should"]
                 ["this" "be"]
                 ["this" "vertical"]]))

(defaggregatefn implode
                ([] [])
                ([word-list] [[word-list]])
                ([word-list word] (conj word-list word)))

(fact "Implodes - http://jimdrannbauer.com/2011/02/04/cascalog-made-easier/"
      (<- [?a ?c]
          ([["this" "should"]
            ["this" "be"]
            ["this" "horizontal"]] ?a ?b)
          (implode ?b :> ?c)) =>
      (produces [["this" ["should" "be" "horizontal"]]]))










