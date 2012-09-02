# Clojure JavaFX Example Project

This is a short example of how to use Clojure to create a "Hello World!" JavaFX application with Clojure and Leiningen.

   Copyright (c) 2012 Raju Bitter rajubitter@gmail.com  
   https://github.com/raju-bitter/clojure-javafx-example  
   MIT license http://www.opensource.org/licenses/mit-license.php  
   Use is subject to license terms.  

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
   THE SOFTWARE.


## Project Setup

### Software Prerequisites
The following software has to be installed on your system to use this project:

    1. Oracle Java 7 SDK for Windows, Mac OS X, or Linux
       http://www.oracle.com/technetwork/java/javase/downloads/index.html

    2. Leiningen 2
       https://github.com/technomancy/leiningen

    3. Apache Maven
       http://maven.apache.org/

The $JAVA_HOME environment variable needs to be set, pointing to the Java 7 SDK installation.

### Installing JavaFX Runtime JAR into local Maven Repository

Get the code for this project using either Github or the file download option. In the project folder, run the following command to install the JavaFX runtime JAR library locally into your Maven repository, using the corresponding version number (currently the Oracle Java 7 JDK includes JavaFX 2.2.0) and folders containing the jfxrt.jar and your Maven repository files.

    mvn deploy:deploy-file -DgroupId=local.oracle  -DartifactId=javafxrt -Dversion=2.2.0 -Dpackaging=jar -Dfile=/usr/lib/jvm/java-7-oracle-64/jre/lib/jfxrt.jar -Durl=file:/home/raju/.m2/repository

Here is the output you should see when installing the artifact:

    raju@T500:~/src/git/clojure-javafx-example$ mvn deploy:deploy-file -DgroupId=local.oracle  -DartifactId=javafxrt -Dversion=2.2.0 -Dpackaging=jar -Dfile=/usr/lib/jvm/java-7-oracle-64/jre/lib/jfxrt.jar -Durl=file:/home/raju/.m2/repository
    [INFO] Scanning for projects...
    [INFO] Searching repository for plugin with prefix: 'deploy'.
    [INFO] ------------------------------------------------------------------------
    [INFO] Building Maven Default Project
    [INFO]    task-segment: [deploy:deploy-file] (aggregator-style)
    [INFO] ------------------------------------------------------------------------
    [INFO] [deploy:deploy-file {execution: default-cli}]
    Uploading: file:/home/raju/.m2/repository/local/oracle/javafxrt/2.2.0/javafxrt-2.2.0.jar
    14588K uploaded  (javafxrt-2.2.0.jar)
    [INFO] Retrieving previous metadata from remote-repository
    [INFO] repository metadata for: 'artifact local.oracle:javafxrt' could not be found on repository: remote-repository, so will be created
    [INFO] Uploading repository metadata for: 'artifact local.oracle:javafxrt'
    [INFO] Uploading project information for javafxrt 2.2.0
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESSFUL
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: 1 second
    [INFO] Finished at: Sat Sep 01 20:51:41 CEST 2012
    [INFO] Final Memory: 6M/73M
    [INFO] ------------------------------------------------------------------------

Run 'lein deps' to download the Maven dependencies, and launch the REPL by running 'lein repl'.

    raju@T500:~/src/git/clojure-javafx-example$ lein repl
    nREPL server started on port 40279
    REPL-y 0.1.0-beta8
    Clojure 1.4.0
        Exit: Control+D or (exit) or (quit)
    Commands: (user/help)
        Docs: (doc function-name-here)
              (find-doc "part-of-name-here")
        Source: (source function-name-here)
              (user/sourcery function-name-here)
    Javadoc: (javadoc java-object-or-class-here)
    Examples from clojuredocs.org: [clojuredocs or cdoc]
              (user/clojuredocs name-here)
              (user/clojuredocs "ns-here" "name-here")
    
Compile the Clojure file in <a href="https://raw.github.com/raju-bitter/clojure-javafx-example/master/src/jfx/app.clj">src/jfx/app.clj</a> using the compile function. This will produce a Java class file in the target folder: target/classes/clj/jfx/App.class 
    user=> (compile 'jfx.app)
    jfx.app

Now you can launch the JavaFX application using the function launch in namespace jfx.app:

    user=> (jfx.app/launch)
    Current state:READY
    Current state:SCHEDULED
    Current state:RUNNING
    Current state:SUCCEEDED
    URL 'http://clojure.org' load completed!
    The button was clicked
    The button was clicked
    nil

Here is a screenshot of the application running. The stage contains a WebView, which loads the URL http://clojure.org, and a button with a click handler:

<img src="https://raw.github.com/raju-bitter/clojure-javafx-example/master/img/javafx-app-screenshot.png" alt="JavaFX app running with Clojure REPL" />

