(ns cascalog-for-dummies.dummy-1
  (:use [clojure.test]
        [cascalog api]
        [midje sweet cascalog]))

(fact "All"
  (<- [?a]
      ([[1]] ?a)) =>
  (produces [[1]]))

