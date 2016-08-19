(ns javasynth.getting-started
  (:import [com.jsyn JSyn]
           [com.jsyn.unitgen LineOut SineOscillator]))

(defn play-sine [freq dur]
  (let [synth (doto (. JSyn createSynthesizer) .start)
        out   (LineOut.)
        sine  (SineOscillator. freq)]
    (.add synth out)
    (.add synth sine)
    (.connect (. sine -output) (. out -input))
    (.start out)
    (Thread/sleep dur)
    (.stop synth)))
