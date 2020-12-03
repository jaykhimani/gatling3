## SSCCE for [Gatling Community Support](https://groups.google.com/g/gatling/c/JUUghQqG_zk)

### Requirement
* Maven 3.5.x
* Java 8

### How To Run
* `mvn clean gatling:test -Dgatling.simulationClass=com.jak.sandbox.gatling.MySimulation -Denv.name=tst`

### Issue
* In my [gatling.conf](src/test/resources/gatling.conf), I've one substitution defined which is set by command line system property (see above)
  ```hocon
    directory {
      #simulations = user-files/simulations # Directory where simulation classes are located (for bundle packaging only)
      resources = src/test/resource/${env.name}     # Directory where resources, such as feeder files and request bodies are located (for bundle packaging only)
      #reportsOnly = ""                     # If set, name of report folder to look for in order to generate its report
      #binaries = ""                        # If set, name of the folder where compiles classes are located: Defaults to GATLING_HOME/target.
      #results = results                    # Name of the folder where all reports folder are located
    }
  ```
* Simulation fails at startup with following error
    ```
    [INFO] --- gatling-maven-plugin:3.1.0:test (default-cli) @ gatling ---
    10:13:54.653 [main][ERROR][ZincCompiler.scala:269] i.g.c.ZincCompiler$ - Compilation crashed
    com.typesafe.config.ConfigException$UnresolvedSubstitution: gatling.conf @ file:/private/tmp/gatling/src/test/resources/gatling.conf: 35: Could not resolve substitution to a value: ${env.name}
            at com.typesafe.config.impl.ConfigReference.resolveSubstitutions(ConfigReference.java:111)
            at com.typesafe.config.impl.ResolveContext.realResolve(ResolveContext.java:179)
            at com.typesafe.config.impl.ResolveContext.resolve(ResolveContext.java:142)
            at com.typesafe.config.impl.ConfigConcatenation.resolveSubstitutions(ConfigConcatenation.java:205)
            at com.typesafe.config.impl.ResolveContext.realResolve(ResolveContext.java:179)
            at com.typesafe.config.impl.ResolveContext.resolve(ResolveContext.java:142)
            at com.typesafe.config.impl.SimpleConfigObject$ResolveModifier.modifyChildMayThrow(SimpleConfigObject.java:380)
            at com.typesafe.config.impl.SimpleConfigObject.modifyMayThrow(SimpleConfigObject.java:313)
            at com.typesafe.config.impl.SimpleConfigObject.resolveSubstitutions(SimpleConfigObject.java:399)
            at com.typesafe.config.impl.ResolveContext.realResolve(ResolveContext.java:179)
            at com.typesafe.config.impl.ResolveContext.resolve(ResolveContext.java:142)
            at com.typesafe.config.impl.SimpleConfigObject$ResolveModifier.modifyChildMayThrow(SimpleConfigObject.java:380)
            at com.typesafe.config.impl.SimpleConfigObject.modifyMayThrow(SimpleConfigObject.java:313)
            at com.typesafe.config.impl.SimpleConfigObject.resolveSubstitutions(SimpleConfigObject.java:399)
            at com.typesafe.config.impl.ResolveContext.realResolve(ResolveContext.java:179)
            at com.typesafe.config.impl.ResolveContext.resolve(ResolveContext.java:142)
            at com.typesafe.config.impl.SimpleConfigObject$ResolveModifier.modifyChildMayThrow(SimpleConfigObject.java:380)
            at com.typesafe.config.impl.SimpleConfigObject.modifyMayThrow(SimpleConfigObject.java:313)
            at com.typesafe.config.impl.SimpleConfigObject.resolveSubstitutions(SimpleConfigObject.java:399)
            at com.typesafe.config.impl.ResolveContext.realResolve(ResolveContext.java:179)
            at com.typesafe.config.impl.ResolveContext.resolve(ResolveContext.java:142)
            at com.typesafe.config.impl.SimpleConfigObject$ResolveModifier.modifyChildMayThrow(SimpleConfigObject.java:380)
            at com.typesafe.config.impl.SimpleConfigObject.modifyMayThrow(SimpleConfigObject.java:313)
            at com.typesafe.config.impl.SimpleConfigObject.resolveSubstitutions(SimpleConfigObject.java:399)
            at com.typesafe.config.impl.ResolveContext.realResolve(ResolveContext.java:179)
            at com.typesafe.config.impl.ResolveContext.resolve(ResolveContext.java:142)
            at com.typesafe.config.impl.ResolveContext.resolve(ResolveContext.java:231)
            at com.typesafe.config.impl.SimpleConfig.resolveWith(SimpleConfig.java:78)
            at com.typesafe.config.impl.SimpleConfig.resolve(SimpleConfig.java:68)
            at com.typesafe.config.impl.SimpleConfig.resolve(SimpleConfig.java:41)
            at com.typesafe.config.ConfigFactory.load(ConfigFactory.java:216)
            at com.typesafe.config.ConfigFactory.load(ConfigFactory.java:116)
            at com.typesafe.config.ConfigFactory.load(ConfigFactory.java:76)
            at io.gatling.compiler.config.CompilerConfiguration$.configuration(CompilerConfiguration.scala:60)
            at io.gatling.compiler.ZincCompiler$.doCompile(ZincCompiler.scala:84)
            at io.gatling.compiler.ZincCompiler$.delayedEndpoint$io$gatling$compiler$ZincCompiler$1(ZincCompiler.scala:265)
            at io.gatling.compiler.ZincCompiler$delayedInit$body.apply(ZincCompiler.scala:40)
            at scala.Function0.apply$mcV$sp(Function0.scala:39)
            at scala.Function0.apply$mcV$sp$(Function0.scala:39)
            at scala.runtime.AbstractFunction0.apply$mcV$sp(AbstractFunction0.scala:17)
            at scala.App.$anonfun$main$1$adapted(App.scala:80)
            at scala.collection.immutable.List.foreach(List.scala:431)
            at scala.App.main(App.scala:80)
            at scala.App.main$(App.scala:78)
            at io.gatling.compiler.ZincCompiler$.main(ZincCompiler.scala:40)
            at io.gatling.compiler.ZincCompiler.main(ZincCompiler.scala)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
            at java.lang.reflect.Method.invoke(Method.java:498)
            at io.gatling.mojo.MainWithArgsInFile.runMain(MainWithArgsInFile.java:50)
            at io.gatling.mojo.MainWithArgsInFile.main(MainWithArgsInFile.java:33)
    ```

### Expected
* When running simulation, `env.name` value should be substituted in `gatling.conf`. 
* Works well with gatling `2.x`. Check out the example with Gatling 2.x at this [Github Repo](https://github.com/jaykhimani/gatling2)