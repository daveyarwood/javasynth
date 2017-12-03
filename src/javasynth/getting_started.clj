(ns javasynth.getting-started
  (:import [com.jsyn JSyn]
           [com.jsyn.unitgen LineOut SineOscillator LinearRamp]))

(defn play-sine [freq dur amp]
  (let [synth (doto (. JSyn createSynthesizer) .start)
        out   (LineOut.)
        ramp  (LinearRamp.)
        sine  (SineOscillator. freq)]
    (.set (. ramp -current) amp)
    (.set (. ramp -input) amp)
    (.set (. ramp -time) (/ dur 1000))
    (.add synth out)
    (.add synth ramp)
    (.add synth sine)
    (.connect (. sine -output) (. out -input))
    (let [now (. synth getCurrentTime)]
      (.start out)
      (.start ramp)
      (. synth (sleepUntil (+ now 0.01)))
      (. synth (sleepUntil (+ now (/ dur 1000))))
      (.stop synth))))
