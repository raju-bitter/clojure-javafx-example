;   Copyright (c) 2012 Raju Bitter rajubitter@gmail.com
;   https://github.com/raju-bitter/clojure-javafx-example 
;   MIT license http://www.opensource.org/licenses/mit-license.php
;   Use is subject to license terms.

;   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
;   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
;   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
;   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
;   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
;   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
;   THE SOFTWARE.

(ns jfx.app
  (:import (javafx.beans.value ChangeListener ObservableValue)
           (javafx.concurrent Worker$State)
           (javafx.event ActionEvent EventHandler)
           (javafx.scene Scene)
           (javafx.scene.control Button)
           (javafx.scene.layout StackPane)           
           (javafx.stage Stage)
           (javafx.scene.web WebView)))

(gen-class
 :name clj.jfx.App
 :extends javafx.application.Application
 :prefix "app-") 

(defn app-start [app ^Stage stage]
  (let [root (StackPane.)
        btn (Button.)
        web-view (WebView.)
        state-prop (.stateProperty (.getLoadWorker (.getEngine web-view)))
        url "http://clojure.org"]
    
    ;; Add a WebView (headless browser)
    (.add (.getChildren root) web-view)
    ;; Register listener for WebView state changes
    (.addListener state-prop
                  (proxy [ChangeListener] []
                    (changed [^ObservableValue ov
                              ^Worker$State old-state
                              ^Worker$State new-state]
                      (println (str "Current state:" (.name new-state)))
                      (if (= new-state Worker$State/SUCCEEDED)
                        (println (str "URL '" url "' load completed!"))))))
    ;; Load a URL
    (.load (.getEngine web-view) url)
    
    ;; add a Button with a click handler class floating on top of the WebView
    (.setTitle stage "JavaFX app with Clojure")
    (.setText btn "Just a button")
    (.setOnAction btn
                  (proxy [EventHandler] []
                    (handle [^ActionEvent event]
                      (println "The button was clicked"))))
    (.add (.getChildren root) btn)
    
    ;; Set scene and show stage
    (.setScene stage (Scene. root 800 600))
    (.show stage)))

(defn app-stop
  "Stop method is called when the application exits."
  [app]
  (println "Exiting application!")
  )

(defn launch
  "Launch a JavaFX Application using class clj.jfx.App"
  []
  (javafx.application.Application/launch clj.jfx.App (into-array String [])))
