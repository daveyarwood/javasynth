(ns javasynth.getting-started
  (:import [com.jsyn JSyn]
           [com.jsyn.unitgen LineOut SineOscillator]))

(defn play-sine [freq dur amp]
  (let [synth (doto (. JSyn createSynthesizer) .start)
        out   (LineOut.)
        sine  (SineOscillator. freq)]
    (.set (. sine -amplitude) amp)
    (.add synth out)
    (.add synth sine)
    (.connect (. sine -output) (. out -input))
    (let [now (. synth getCurrentTime)]
      (.start out)
      (. synth (sleepUntil (+ now (/ dur 1000))))
      (.stop synth))))
