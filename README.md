# noise

# noiseMap

bugs list:

1: cannot find some classes in Eclipse.

    Error configuring application listener of class org.springframework.web.context.ContextLoaderListener java.lang.ClassNotFoundException: org.springframework.web.context.ContextLoaderListener
    
solution:
This exception was caused when i did not add the maven dependencies to the build path. I was using eclipse with maven, so had to include the maven dependencies in the build path as explained below.
See the post for the solution. It is a simple eclipse Deployment Assembly configuration fix. Took me about 30 seconds and now I can debug my Spring webapp in Eclipse.

Here are the relevant steps from the post:

Adding Maven dependencies in project web deployment assembly :

> Open project properties(example: In project explorer rightClick on project name to select "Properties").

> Select "Deployment Assembly".

> Click "Add..." button on the right navigation.

> Select "Java Build Path Entries" from menu of Directive Type and then click "Next".

> Select "Maven Dependencies" from Java Build Path Entries menu

> click "Finish".

Now the "Maven Dependencies" should be added to the Web Deployment Assembly and it should run.

2: cannot find controller, the url cannot be mapped to a existing controller.

    org.springframework.web.servlet.PageNotFound noHandlerFound No mapping found for HTTP request with URI
    
solution: Make sure in dispathcer-servlet.xml, spring can scan your components.

    <context:component-scan base-package="zqin.*" />

3: use lombok to generate setter and getter methods in POJO. use @Data, but in Eclipse, we must add 

    -Xbootclasspath/a:lombok.jar
    -javaagent:lombok.jar
    
in eclipse.ini and restart, update maven project.   

4: jsp file error in Eclipse

solution: add tomcat lib into java build path. 

5: in Eclipse, web-service can not run as server

solution : 

> Open project properties(example: In project explorer rightClick on project name to select "Properties").

> Select "properties—>project facets".

> Choose "Dynamic Web Module" ,select 2.3 version.

> Select "Further configuration available…" and set web.xm  "src/main/webapp".

> click "Apply".