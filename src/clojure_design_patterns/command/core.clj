(ns clojure-design-patterns.command.core)

;; Command design pattern

;; Command pattern is useful when you have objects that perform a specific action, 
;; and you need to have a way to execute these actions independently of the objects that make them.

(defn add [x y]
  (+ x y))

(defn substract [x y]
  (if (> x y)
    (- x y)
    (- y x)))

(defn perform-operation [operation x y]
  (operation x y))

(perform-operation add 1 2)
(perform-operation substract 7 15)

;; Implementing undo/redo functionality in a text editor.
(def commands (atom []))
(def undo-commands (atom []))

(defn add-command [command]
  (reset! commands (conj @commands command))
  #_(reset! undo-commands []))

(defn remove-all-commad[]
  (reset! commands []))

(defn undo[]
  (let [command (peek @commands)]
    (when command
      (reset! commands (pop @commands))
      (reset! undo-commands (conj @undo-commands command))
      (command))))

(defn redo []
  (let [command (peek @commands)]
    (when command
      #_(reset! undo-commands (conj @undo-commands command))
      #_(reset! commands (pop @commands))
      (command))))

;; Parametrizing buttons in a graphical user interface with different actions.

(defn button [{:keys [text action]}]
  (action))

(defn create-button [text action]
  (button {:text text :action action}))

(def btn1 (create-button "Click me" #(println "Button 1 clicked")))
(def btn2 (create-button "Click me too" #(println "Button 2 clicked")))