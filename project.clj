(defproject cascalog-for-dummies "0.1.0-SNAPSHOT"

  :description "Guess what!"

  :url "https://github.com/amanas/cascalog-for-dummies"

  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}

  :repositories {"conjars" "http://conjars.org/repo"}

  :dependencies [[cascalog/cascalog-core "2.0.0"]
                 [org.clojure/clojure "1.5.1"]]

  :profiles {:dev {:dependencies [[cascalog/midje-cascalog "2.0.0"]
                                  [midje "1.5.1"]
                                  [org.apache.hadoop/hadoop-core "1.1.2"]]

                   :plugins      [[lein-midje "3.0.1"]]}}
  )
