# Sirius Server

The Sirius Server initiative aims at leveraging all the features of Sirius in the web. This work is based on everything built in the Sirius project over the years without any big bang where we would rewrite everything from scratch. The Sirius Server is an Eclipse RCP product and a Docker container in which it is contained which allow you to use Sirius in the web. 

It is based on the same plugins and features used in the regular version of Sirius. Some work is being done to refactor some pieces of code for this new environment and its constraints. As an example, this code will not depend on the user interface since it will have to run on a server.


## Code

The code of the Sirius Server is located in two separate Git repositories:

* The front-end is hosted on the [Sirius Components repository](https://www.github.com/eclipse/sirius-components)
* The back-end is hosted on the main [Sirius repository](https://git.eclipse.org/c/sirius/org.eclipse.sirius.git)

Only a subset of the components available on the main Sirius repository are involved in the Sirius Server. The main components of the server are available under the namespace ```org.eclipse.sirius.server```.

The entry point of the server is the component ```org.eclipse.sirius.server```. This component is used to start the Jetty-based server, configure it and register all the services available on the server. It contains the main extension points used to contribute to both the HTTP and Web Socket API of the server. This component is also the one which uses most of the environment variables named in this document.


### RCP Product

The RCP product is based on the following components:

* ```org.eclipse.sirius.server```, the entry point starting the Jetty server
* ```org.eclipse.sirius.server.feature```, the feature of the product
* ```org.eclipse.sirius.server.application```, the IApplication of the product
* ```org.eclipse.sirius.server.product```, the RCP product
* ```org.eclipse.sirius.server.product.feature```, the feature of the product 

On top of those, you will encounter those additional components:

* ```org.eclipse.sirius.server.backend```
* ```org.eclipse.sirius.server.diagram```
* ```org.eclipse.sirius.services.diagram```
* ```org.eclipse.sirius.server.graphql```
* ```org.eclipse.sirius.services.graphql```
* ```org.eclipse.sirius.server.frontend```
* ```org.eclipse.sirius.server.frontend.workflow```
* ```org.eclipse.sirius.services.common```
* ```org.eclipse.sirius.server.images```

The ```org.eclipse.sirius.server.backend``` component is a soon-to-be deprecated HTTP API which was released with the first implementation of the workflow. This HTTP API based on the REST principles gave users access to some information on projects, semantic resources and representations. It is replaced by the GraphQL based API.

The Sirius Server Diagram component is used to provide a Web Socket API to manipulate diagrams. This component is only responsible for the network communication along with the JSON serialization and deserialization. All the behavior related to diagrams is located in the component ```org.eclipse.sirius.services.diagram```. This component is interacting with the regular Sirius Diagram components in a similar fashion as the Sirius diagram editor. The Sirius Services Diagram component is mainly responsible for providing a simple API to manipulate Sirius diagrams.

The Sirius Server GraphQL component provides the entry point used to manipulate the GraphQL API of Sirius on the web. This component uses ```org.eclipse.sirius.services.graphql``` which provides the GraphQL interpreter. The Sirius Services GraphQL component can be manipulated programmatically without any network interaction.

The Sirius Server FrontEnd component is only used to provide some static resources. Those resources (html, css, js) are used to create the user interface of the Sirius Server. They are located in a folder named ```sirius-frontend``` at the root of the plugin. Those files are the result of the build of the Sirius Components repository. The Sirius Server Workflow Frontend is used to provide all the frontend resources for the workflow integatred in Eclipse. This component is not part of the Sirius Server feature and won't be included in the Sirius Server product. The Sirius Server Images component is used to provide access to images inside of plugins and images for EObjects.

All the plugins necessary to have the full experience of the Sirius Server will be added to the feature ```org.eclipse.sirius.server.feature```. Additional features and plugins required for the creation of the product will be added to the feature ```org.eclipse.sirius.server.product.feature```. Optional features and plugins used to help managing the Sirius Server will be directly added to the product ```org.eclipse.sirius.server.product```. Among those plugins, you will find some Eclipse Equinox utility plugins like the support for ```p2```. The feature ```org.eclipse.equinox.p2.core.feature``` comes with the p2 console for example and the feature ```org.eclipse.equinox.p2.extras.feature``` comes with the p2 director application used to easily install new dependencies into the product.


### Autostart

While using the Sirius Server inside a regular desktop application, it is useful to start the server when the Eclipse application is launched. As such, the plugin ```org.eclipse.sirius.server.ui.autostart``` is used to start the Sirius server using the early startup extension point. Since this extension point depends on the user interface (hence the name), it is not included in the Sirius Server product which uses its `IApplication` to start the server automatically.

This plugin is also available by itself in the feature ```org.eclipse.sirius.server.ui.autostart.feature```.


## Build

There are two parts in the build of the Sirius Server. First, a regular Maven build, connected to the main Sirius build, which will create a product for our server. Second, a Docker build used to create the Docker image for the product created.


### Maven

The build of the Sirius Server uses the headless target platform of Sirius in order to ensure that no user interface dependencies are included in the final product. In order to launch a build of the Sirius Server, use the following command at the root of the Sirius repository:

```
mvn clean verify -Pheadless -Pheadless-server -Dplatform-version-name=photon
```

You could also run a full build of Eclipse Sirius if you want with:

```
mvn clean verify -Pheadless -Pheadless-server -Pfull -Dplatform-version-name=photon
```

Once the build is completed, you can find the Eclipse RCP-based products in the folder ```package/org.eclipse.sirius.server.product/target/products```. You can unzip the one matching your platform and run it as a regular executable. If you want to start the Sirius Server without a Docker container, open a terminal and execute the RCP application (`sirius-server`).

It is recommended to use a terminal to start the Sirius Server instead of executing it by simply double clicking it since you want have access to the log easily.


### Docker

A Docker image for the Sirius Server can be created thanks to the `Dockerfile` available in the Sirius repository. The Docker image created is published on the [Docker repository of the Sirius Server](https://hub.docker.com/r/eclipsesirius/sirius-server).

Navigate to the folder ```packaging/org.eclipse.sirius.server.product``` and run the following command to build the Docker image using the product built with Maven:

```
docker build -t eclipsesirius/sirius-server -f ./Dockerfile .
```

This command will create a Docker image named `eclipsesirius/sirius-server` (and tagged `latest` by default) using the Dockerfile in the current folder and with the current folder as the context of the build (the dot at the end of the command). The context of the build is important since it is the location in which Docker will look for the product built with Maven (in the `target/products` folder). The Docker image cannot be created if the Maven-based build has not been executed before.

You can check that the Docker image has been properly created thanks to the following command:

```
docker images
```

If you want to remove an image built previously, you can use ```docker rmi IMAGE_ID```. Once the image has been created, you can create and start a container from this image with:

```
docker run -p 8080:8080 -i -t eclipsesirius/sirius-server:latest
```

This command will create a Docker container from the image ```eclipsesirius/sirius-server:latest```. It will also connect the port 8080 of the host machine to the port 8080 of the container. As such, a request on a web browser to ```http://localhost:8080``` will be redirected to the Docker container, allowing you to test the server inside of the container from your host machine. 

The two options ```-t``` and ```-i``` will be respectively used to allocate a pseudo-tty allowing you to send commands to the container and keeping ```STDIN``` open. With this command, you terminal will now be connected to the Docker container started.

In this container, you will have the ability to run regular unix commands like ```pwd```, ```top``` or even ```ls```. Once connected to the container, you will be located in the folder ```/home/developer/sirius-server``` where the product has been copied. You can start the Sirius Server with the following command:

```
./sirius-server
```

When the server will start it will display some logs in the console. The Docker container does not start the Sirius Server automatically. This choice has been made in order to let people build their own Docker container from this one with having to handle the constraint of an autostart. It lets others retrieve our container, add the dependencies and then start the container instead of retrieving it, stopping the server started automatically, adding their dependencies and restarting it.

You can stop the server with the command ```exit```. Once stopped, you can close the connection between your terminal and the Docker container with ```exit```. This should stop the container.

If you want to see all the containers currently running, use ```docker ps```. In order to see the container available, including the container which are not running anymore, use ```docker ps -a```. To stop manually a container, you can use ```docker stop CONTAINER_ID``` and finally to destroy the container, use ```docker rm CONTAINER_ID```.

#### Official image

The Docker image of the Sirius project is available on the [EclipseSirius Docker Hub](https://hub.docker.com/r/eclipsesirius/sirius-server/) account. This image can be pulled using:

```
docker pull eclipsesirius/sirius-server
```

You can then launch it using the previous command:

```
docker run -p 8080:8080 -i -t eclipsesirius/sirius-server:latest
```

This image will be tagged for each release of Eclipse Sirius just like the main artifacts of the project.

#### Custom Docker image

If you want to build a custom version of the Sirius Server container with your own metamodels, you can use a Dockerfile inspired by the one below.

```docker
FROM eclipsesirius/sirius-server:latest

RUN ./sirius-server -clean -application org.eclipse.equinox.p2.director -noSplash -repository "http://www.example.org/updatesite" -installIU "org.example.feature.feature.group" -vmargs -Djava.awt.headless=true

EXPOSE 8080
ENTRYPOINT ["./sirius-server"]
```

With a Dockerfile such as this one, you will create a brand new Docker image with your feature ```org.example.feature.feature.group``` installed from the update site ```http://www.example.org/updatesite```.

You can now publish your Docker image and let your users consume it. Have a look at the p2 documentation for more information on the p2 director and the p2 console which is also available thanks to the OSGi console in the Sirius Server.


## Start

Once the Sirius Server is up and running, it will log some information in the console. The first messages should have the following content:

```
2018-08-08 10:58:22.688:INFO::main: Logging initialized @34626ms to org.eclipse.jetty.util.log.StdErrLog
2018-08-08 10:58:23.081:INFO:oejs.Server:main: jetty-9.4.10.v20180503; built: 2018-05-03T15:56:21.710Z; git: daa59876e6f384329b122929e70a80934569428c; jvm 1.8.0_20-b26
2018-08-08 10:58:23.130:INFO:oejs.session:main: DefaultSessionIdManager workerName=node0
2018-08-08 10:58:23.131:INFO:oejs.session:main: No SessionScavenger set, using defaults
2018-08-08 10:58:23.134:INFO:oejs.session:main: node0 Scavenging every 660000ms
2018-08-08 10:58:23.144:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@522a8886{/api,null,AVAILABLE}
2018-08-08 10:58:23.156:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@373bfbf6{/ws,null,AVAILABLE}
2018-08-08 10:58:23.157:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@47c89a33{/workflow,null,AVAILABLE}
2018-08-08 10:58:23.158:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@296ecb77{/images,null,AVAILABLE}
2018-08-08 10:58:23.187:INFO:oejsh.ContextHandler:main: Started o.e.j.s.ServletContextHandler@2a0fb087{/,null,AVAILABLE}
2018-08-08 10:58:23.261:INFO:oejs.AbstractConnector:main: Started ServerConnector@4e161f41{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
2018-08-08 10:58:23.263:INFO:oejs.Server:main: Started @35201ms
```

The logs start with the version of Jetty used by the server along with some settings.

Then you can see the details of the `SessionIdManager` used by Jetty. The `SessionIdManager` is used to collect and store some information across time about the end users. Those information can be manipulated thanks to the servlet API. There are multiple session manager implementations in Jetty (in memory, in a database, in MongodDB or even in Google Cloud Datastore). We do not configure anything regarding session management for the moment so the logs indicate that we are using the `DefaultSessionIdManager` without any `SessionScavenger` configured. 

After that, you will find the list of context handlers used by the server. Here we have the main context handlers registered:

* The context handler of the HTTP API ```{/api,null,AVAILABLE}```
* The context handler of the Web Socket API ```{/ws,null,AVAILABLE}```
* The context handler of the front-end of the workflow ```{/workflow,null,AVAILABLE}```
* The context handler of the Images API ```{/images,null,AVAILABLE}```
* The context handler of the front-end ```{/,null,AVAILABLE}```

The context handler of the HTTP API will be used for all requests on ```http://localhost:8080/api```. The context handler of the Web Socket API will be used for all requests on ```http://localhost:8080/ws```. Finally, the context handler of the front-end will handle all other requests on ```http://localhost:8080/```.

After that, you can see the protocols supported by the Sirius Server and the ports used, in this case ```{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}```.

By default the Sirius Server will start on a random available port so look into the console to find the port selected. An environment variable is available to configure the port to use (more about that later in this document). The product created by the build uses this environment variable to start the server on the port ```8080```. While this port can create some conflict with other tools which may use the same port, inside the Docker container we know that nothing else will use the same port.

## OSGi Console

The Sirius Server product uses the ```-console``` option which launches the Equinox OSGi console when the product is starting. With this console, you will be able to inspect the state of the product and modify it. Let's have a look at some of the most common commands that you can run in the console.

### State of the components

The first thing that you can try with the Sirius Server console is to have a look at the components available and their state. You can find the list of the components installed with the command ```ss``` which can be limited to a specific scope with ```ss org.eclipse.sirius.server``` for example. The previous command may give you the following result:

```
osgi> ss org.eclipse.sirius.server
"Framework is launched."


id	State       Bundle
1860	ACTIVE      org.eclipse.sirius.server_6.6.2.qualifier
1861	STARTING    org.eclipse.sirius.server.application_6.6.2.qualifier
1862	ACTIVE      org.eclipse.sirius.server.backend_6.6.2.qualifier
1863	ACTIVE      org.eclipse.sirius.server.diagram_6.6.2.qualifier
1864	ACTIVE      org.eclipse.sirius.server.frontend_6.6.2.qualifier
1865	ACTIVE      org.eclipse.sirius.server.graphql_6.6.2.qualifier
1866	ACTIVE      org.eclipse.sirius.server.images_6.6.2.qualifier
```

You can see the components matching the given scope, their id and their state.

### Extension points and extension

The OSGi console can let you see the various extension points available and their extensions. To have a look at the extension points you will have to use ```ns``` directly or with a scope like ```ns org.eclipse.sirius.server```:

```
osgi> ns org.eclipse.sirius.server
Extension point(s):
-------------------
org.eclipse.sirius.server.siriusServerConfigurator [from org.eclipse.sirius.server]
org.eclipse.sirius.server.siriusServerService [from org.eclipse.sirius.server]
org.eclipse.sirius.server.siriusServerEndpointConfigurationProvider [from org.eclipse.sirius.server]
```

In order to see the extensions of a specific extension point, use ```pt``` with the name of the extension point such as ```pt org.eclipse.sirius.server.siriusServerService```:

```
osgi> pt org.eclipse.sirius.server.siriusServerService
Extension point: org.eclipse.sirius.server.siriusServerService [from org.eclipse.sirius.server]

Extension(s):
-------------------
org.eclipse.sirius.server.backendServices [from org.eclipse.sirius.server.backend]
org.eclipse.sirius.server.graphql.GraphQLService [from org.eclipse.sirius.server.graphql]
```

You can see here the components which are contributing to the HTTP API of the Sirius Server.

### Starting and stopping a component

You can manipulate the lifecycle of the components quite easily from the console. It allows you to install, start, stop and even uninstall any component. If you want to install a components from an update site, you would need to have access to the p2 console. Contrary to the OSGi console, the p2 console is not started automatically but we can start it manually. For that, you need to find the id of the p2 console component using the following command:

```
osgi> ss console
"Framework is launched."


id    State       Bundle
993   ACTIVE      org.eclipse.equinox.console_1.3.0.v20180119-0630
1015  STARTING    org.eclipse.equinox.p2.console_1.1.0.v20180130-1836
```

then you can start it with the command ```start```:

```
osgi> start 1015
```

its new state is available with ```ss console```

```
osgi> ss console
"Framework is launched."


id    State       Bundle
993   ACTIVE      org.eclipse.equinox.console_1.3.0.v20180119-0630
1015  ACTIVE      org.eclipse.equinox.p2.console_1.1.0.v20180130-1836
```

and you have now access to the [p2 commands](https://wiki.eclipse.org/Equinox/p2/Console_Users_Guide) in the OSGi console. To find more about the p2 commands, use ```help```:

```
osgi> help
---Configurator Commands---
	confapply [<config URL>] - Applies a configuration

---p2 Provisioning Commands.---
---Repository Commands.---
	provaddrepo <repository URI> - Add specified URI as metadata and artifact repository.
	provdelrepo <repository URI> - Remove specified metadata and artifact repository.
	provaddmetadatarepo <repository URI> - Add specified URI as metadata repository.
	provdelmetadatarepo <repository URI> - Remove specified metadata repository.
	provaddartifactrepo <repository URI> - Add specified URI as artifact repository.
	provdelartifactrepo <repository URI> - Remove specified artifact repository.
```

The output of the help command is truncated because there are dozens of commands in the OSGi console. The p2 provisioning commands can let you add an update site to the product and install any installation unit (plugin or feature) from this update site into the product. Those commands are not available until the p2 console component is active.


## Protocols

The Jetty server will be used to communicate with Sirius using HTTP(S) and Web Socket.

HTTP will be used by those who want to receive a snapshot of some data from the server while Web Socket will be used to communicate over time with the server. With HTTP, the server will wait for a request to compute some data and return it in a response.

With Web Socket, the client will be connected to the server and both the clients and the server will send and receive messages. As such, with Web Socket, a client can listen for messages emitted from the server without ever sending a message to the server. The Web Socket protocol does not appear in the list of context handlers of the server in the logs because a Web Socket connection is started from an HTTP endpoint. After a handshake the protocol is switched from HTTP to Web Socket.

One could ask why we are not using Web Socket everywhere instead of HTTP because Web Socket can do what HTTP can (request then response). Web Socket is more complex to manipulate and HTTP is way more supported so Web Socket will only be used by features where clients need to react to events on the server such as diagram manipulation. Server Side Events (SSE) could be used instead of Web Socket but since this protocol is way less powerful than Web Socket, it should mostly be limited to notification systems. We do not plan to use SSE for now.

## HTTP

The HTTP API can be extended thanks to the extension point ```org.eclipse.sirius.server.siriusServerService```. With this extension point, you can contribute an instance of ```org.eclipse.sirius.server.api.ISiriusServerService```. A Sirius Server service defines how to handle some HTTP requests on a specific subpath.

All extensions contributed to this extension point will be available on a subpath of ```http://localhost:8080/api``` as defined by the servlet context handler of the HTTP API. For example, the Sirius Server service instance below will respond to HTTP requests on ```http://localhost:8080/api/projects/{projectName}```

```java
import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_NOT_FOUND;
import static org.eclipse.sirius.server.api.SiriusServerResponse.STATUS_OK;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.sirius.server.api.ISiriusServerService;
import org.eclipse.sirius.server.api.SiriusServerPath;
import org.eclipse.sirius.server.api.SiriusServerResponse;

@SiriusServerPath("/projects/{projectName}")
public class TestSiriusServerService implements ISiriusServerService {
    private static final String PROJECT_NAME = "projectName"; //$NON-NLS-1$

    @Override
    public SiriusServerResponse doGet(HttpServletRequest request, Map<String, String> variables, String remainingPart) {
        Optional<String> optionalProjectName = Optional.ofNullable(variables.get(PROJECT_NAME));
        return optionalProjectName.map(projectName -> new SiriusServerResponse(STATUS_OK, "Hello " + projectName)).orElse(new SiriusServerResponse(STATUS_NOT_FOUND));
    }
}
```

In order to send a HTTP request to this endpoint, you could use the following JavaScript code:

```javascript
const getProject = async (projectName) => {
  const jsonResponse = await fetch(`http://localhost:8080/api/projects/${projectName}`);
  const response = await jsonResponse.json();
  console.log(response)
};

getProject('test');
```

A REST API is available for legacy reasons, this API will be deprecated in the months to come. This REST API can be found in the component ```org.eclipse.sirius.server.backend```.


### Cross Origin Resource Sharing

When some JavaScript code in the web browser sends a request to a server, the response can only be processed if the JavaScript client code comes from the same domain as the HTTP API.

If some JavaScript from ```http://www.domainA.com``` tries to contact the HTTP API of ```http://www.domainB.com``` then the request will fail unless the server from the domain B accept to be contacted from the domain A. You will encounter this issue when you are developing an application with the Sirius Server. Your instance of the Sirius Server may be started on ```http://localhost:8080``` while the development server of your front-end may be started on ```http://localhost:3000```.

In this configuration, requests coming from your front-end will be considered as coming from a different domain and as such they will fail. While some options are available in front-end development tools to fix this issue, you can also fix it by setting some Cross Origin Resource Sharing (CORS) headers in your responses. With those headers, your web browser will accept to process the responses from the server. To configure the CORS, use the dedicated environment variables to allow, for example, all the origins to request data from your server with ```-Dorg.eclipse.sirius.server.cors.allowed.origin=*```.

## Image HTTP API

The HTTP API also provides access to static images located inside any plugin in the server. For that, use HTTP request with the following structure:

```
http://localhost:8080/images/PLUGIN_NAME/PATH_TO_IMAGE
```

For example ```http://localhost:8080/images/org.eclipse.emf.ecore.edit/icons/full/obj16/EClass.gif```. This API can let you easily retrieve images used by tools and mappings in a Sirius diagram. The HTTP API can also be used to retrieve the image provided by the EMF label provider with request such as:

```
http://localhost:8080/images/PROJECT_NAME/PATH_TO_RESOURCE?fragment=EOBJECT_FRAGMENT
```

For example, ```http://localhost:8080/images/org.eclipse.emf.examples.library/model/extlibrary.ecore?fragment=//Writer```.

## GraphQL HTTP

A new version of the HTTP API has been created using GraphQL Java in the components ```org.eclipse.sirius.server.graphql``` and ```org.eclipse.sirius.services.graphql```. The GraphQL interpreter can be manipulated programmatically without any network requests thanks to the component ```org.eclipse.sirius.services.graphql```.

The GraphQL endpoint can be accessed at ```http://localhost:8080/api/graphql```. All GraphQL requests should use the ```POST``` HTTP verb. You can communicate with the GraphQL endpoint using a tool like [Insomnia](https://insomnia.rest). In order to send a GraphQL payload, you can send the following content in a `POST` request with the ```application/json``` content type.

```json
{
  "query": "...",
  "operationName": "...",
  "variables": {
    "key": "value"
  }
}
```

The fields ```operationName``` and ```variables``` are both optional. For example a simple query requesting the number of projects on the server would look like this:

```
POST http://localhost:8080/api/graphql
Content-Type: application/json

{
  "query": "{ viewer { projects { totalCount } }}"
}
```

If you want to execute a GraphQL Query from JavaScript, you can use the following code:

```javascript
const performQuery = async (query, operationName, variables) => {
  const body = {
    query,
    operationName,
    variables
  };
  const jsonResponse = await fetch('http://localhost:8080/api/graphql', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json; charset=utf-8'
    },
    body: JSON.stringify(body)
  });
  const response = await jsonResponse.json();
  console.log(response);
};

const query = `
query getProjectName($projectName: String!) {
  viewer {
    project(name: $projectName) {
      name
    }
  }
}
`;

const operationName = 'getProjectName';
const variables = {
  projectName: 'test'
};

performQuery(query, operationName, variables);
```


### Entry Points

We have one entity used as the entry point of the GraphQL schema, our type ```User``` which is accessible thanks to the field ```viewer```. Since we do not have currently an authentication system for the Sirius Server, an empty instance of user is always created from scratch for each request. This instance does not contain any data but its an entry point from which we can access to various other entities.

For each of those entities, we have two entry points, one to retrieve the collection of entities and another one to retrieve one specific entity. This comes from the fact that users may want to access for example with those two URLs:

* http://localhost:8080/projects
* http://localhost:8080/projects/org.eclipse.sirius

The first request would need to access the collection of projects with some pagination using:

```
{
  viewer {
    projects(first: 10) {
      // See the next section for additional details on the pagination strategy
    }
  }
}
```

The second one would need to access one specific project directly with a request like this one:

```
{
  viewer {
    project(name: "org.eclipse.sirius") {
      name
    }
  }
}
```

In a similar fashion, we have access directly to one metamodel and the collection of all the metamodels with respectively ```ePackage(nsURI: string)``` and ```ePackages(...)```.


### Pagination Strategy

There are various pagination strategies for GraphQL, in the Sirius Server we have decided to adopt the Facebook Relay pagination strategy. This strategy is based on a [specification](https://facebook.github.io/relay/graphql/connections.htm) of the schema of the pagination.

We started by using a very straightforward approach to the pagination with simple collections represented by fields with some arguments (first, after):

```
{
  viewer {
    projects(first: 10, after: 30) {
      name
    }
  }
}
```

This strategy allows us to iterate over a collection of data but it does not give us some information needed to properly paginate over some data on the front-end. With this approach, we do not have the total number of elements available and we can't have some information on the relationship between the elements. It also does not give us the ability to indicate if there are next or previous pages. This is why we have moved to same strategy as Facebook, the previous example would be then expressed like this:

```
{
  viewer {
    projects(first: 10, after: <CURSOR>) {
      totalCount
      pageInfo {
        hasNextPage
        hasPreviousPage
      }
      edges {
        node {
          name
        }
        cursor
      }
    }
  }
}
```

We can also move backward with this pagination strategy:

```
{
  viewer {
    projects(last: 10, before: <CURSOR>) {
      totalPageCount
      pageInfo {
        hasNextPage
        hasPreviousPage
      }
      edges {
        node {
          name
        }
        cursor
      }
    }
  }
}
```


### Schema

The GraphQL schema of the Sirius Server is composed of several domains interconnected:

* Workspace
* Sirius
* EMF

Those domains are linked on with the others by a complex set of relationships. Those relationships are defined using connection and edge types for multi-valued relationships.


#### Workspace

The Workspace domain of the GraphQL schema is used to manipulate the resources available on the server. It defines the following types:

- Resource
- Container
- Project (implements Resource and Container)
- Folder (implements Resource and Container)
- File (implements Resource)


#### Sirius

The Sirius domain of the GraphQL schema is used to manipulate viewpoints, representation descriptions and representations. It defines the following types:

- Viewpoint
- Representation Description
- Diagram Description
- Representation
- Diagram


#### EMF

There are two sub-domains in the GraphQL schema related to the manipulation of EMF concepts. First, a collection of static concepts parameterized by a metamodel and second, a dynamic API to query any EMF model. The static concepts are only used for the moment for the Ecore metamodel.


#### GraphQL SDL Schema

In order to retrieve a human-readable version of the GraphQL schema used by the server, you can use the following piece of code:

```javascript
const fetch = require("node-fetch");
const { introspectionQuery, buildClientSchema, printSchema } = require("graphql");
const fs = require("fs");

fetch("http://localhost:8080/api/graphql", {
  method: "POST",
  headers: { "Content-Type": "application/json" },
  body: JSON.stringify({ query: introspectionQuery })
})
  .then(res => res.json())
  .then(res => {
    fs.writeFileSync("result.json", JSON.stringify(res.data, null, 2));
    const introspectionSchemaResult = JSON.parse(fs.readFileSync("result.json"));
    const graphqlSchemaObj = buildClientSchema(introspectionSchemaResult);
    const sdlString = printSchema(graphqlSchemaObj);
    console.log(sdlString);
  });
```

In order to run this code, you can copy it in a file named ```app.js``` and then run the following commands:

```
npm init --yes
npm install graphql
npm install node-fetch
node app.js
```

The Sirius Server needs to be up and running for this code to work. It will request the GraphQL schema of the server thanks to the reflective API of GraphQL and print it in the Standard Definition Language (SDL). The schema printed will have some differences with the schema created programmatically:

- The type are ordered alphabetically which can make it difficult to find related types
- Directives do not appear in the SDL


### Introspection

GraphQL allows users to inspect the structure of the GraphQL schema. For example, you can start with the following request to retrieve the types of the schema:

```
{
  __schema {
    types {
      name
    }
  }
}
```

Tools like [Insomnia](https://insomnia.rest) or GraphiQL are parameterized by the schema to help you write your GraphQL queries.


### Cost

In order to protect the GraphQL server from malicious queries a mechanism has been configured to limit the amount of information retrieved by a query. Some fields of the schema have been decorated with a directive named ```cost``` which defines the cost of the request of the field. A field decorated with the cost directive can also have ```multipliers``` indicating which argument of the field can multiply the cost of the request. A GraphQL query cannot request more than 100 points of data.

As an example, requesting a project may have a cost of 5 and its multipliers are both the argument ```first``` and ```last```.

```
type User {
  projects(first: int, last: int): UserProjectConnection @cost(complexity: 5, multipliers: ["first", "last"])
}
```

This way, if an user send the following query, it would cost 105 points (21 * 5) and thus the query would be rejected.

```
query {
  viewer {
    projects(first: 21) {
      ...
    }
  }
}
```

The value of the complexity of each field will be modified in the future, those values are not set in stone.


## Web Socket

The Web Socket API can be extended thanks to the extension point named ```org.eclipse.sirius.server.siriusServerEndpointConfigurationProvider```. This extension point gives you the ability to contribute an instance of ```org.eclipse.sirius.server.api.ISiriusServerEndpointConfigurationProvider```.

All the contributions to this extension point will be available on a subpath of ```http://localhost:8080/ws``` as defined by the servlet context handler of the Web Socket API. The endpoint configuration provider will return a configuration of the Web Socket endpoint such as:

```java
public class TestEndpointConfigurationProvider implements ISiriusServerEndpointConfigurationProvider {

    private static final String PATH = "/test"; //$NON-NLS-1$

    @Override
    public ServerEndpointConfig getEndpointConfiguration() {
        // @formatter:off
        return Builder.create(TestEndpoint.class, PATH)
                .configurator(new TestEndpointConfigurator())
                .build();
        // @formatter:on
    }

}
```

This endpoint configuration provider will indicate the endpoint to create and its configurator. The configurator should be provided otherwise the code would not work in an OSGi context (it would delegate everything to a `ServiceLoader` which has to be configured specifically to work in such context). The configurator returned has to delegate everything to the container default configurator.

```java
public class TestEndpointConfigurator extends Configurator {

    private ContainerDefaultConfigurator delegate = new ContainerDefaultConfigurator();

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return this.delegate.getEndpointInstance(endpointClass);
    }

    @Override
    public boolean checkOrigin(String originHeaderValue) {
        return this.delegate.checkOrigin(originHeaderValue);
    }

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        this.delegate.modifyHandshake(sec, request, response);
    }

    @Override
    public String getNegotiatedSubprotocol(List<String> supported, List<String> requested) {
        this.delegate.getNegotiatedSubprotocol(supported, requested);
    }

    @Override
    public List<Extension> getNegotiatedExtensions(List<Extension> installed, List<Extension> requested) {
        this.delegate.getNegotiatedExtensions(installed, requested);
    }

}
```

The ContainerDefaultConfigurator cannot be extended directly since this class is final.


The implementation of the endpoint can then be created. It will be used to configure a message handler for the Web Socket session once the connection is opened.

```java
public class TestEndpoint extends Endpoint {
    @Override
    public void onOpen(Session session, EndpointConfig config) {
        session.addMessageHandler(new TestMessageHandler(session));
    }

    @Override
    public void onClose(Session session, CloseReason closeReason) {
        super.onClose(session, closeReason);
    }
}
```

The message handler is user to receive and send messages to the client. To send messages to the clients, it needs a reference to the Web Socket session.

```java
public class TestMessageHandler implements Whole<String> {

    private Session session;

    public TestMessageHandler(Session session) {
        this.session = session;
    }

    @Override
    public void onMessage(String message) {
        System.out.println(message);
        session.getAsyncRemote().sendText("Hello");
    }

}
```

If you want to execute send and receive some Web Socket messages, you can use the following JavaScript code:

```javascript
const websocket = new WebSocket(`ws://localhost:8080/ws/test`);
websocket.onopen = (event) => {
  const message = JSON.stringify({
    hello: 'world'
  });
  websocket.send(message);
};
websocket.onmessage = (event) => {
  console.log(event.data);
};
```

With this code, once the client is connected it will send ```{ "hello": "world" }``` to the server which will print it in the console. After that, the server will send ```Hello``` back to the client which will print it too in its console.


## Graphical Server Protocol

An API based on Web Socket is available in order to manipulate Sirius diagrams. This API is available on ```http://localhost:8080/ws/diagrams```. Have a look at the [Graphical Server Protocol](https://obeonetwork.github.io/GraphicalServerProtocol/) to find more about the way you can interact with this endpoint.


## Front-End

The front-end is accessible on ```http://localhost:8080/```. It is built from the [Sirius Components repository](https://www.github.com/eclipse/sirius-components) on Github.


## System Properties

The following system properties are available to configure various aspects of the server's behavior:

* ```org.eclipse.sirius.server.cors.allowed.methods``` - The constant used to specify allowed methods. Expects a list of string with comma separated values.
* ```org.eclipse.sirius.server.cors.allowed.headers``` - The constant used to specify allowed headers. Expects a list of string with comma separated values.
* ```org.eclipse.sirius.server.cors.allowed.origins``` - The constant used to specify allowed origins. Expects a list of string with comma separated values.
* ```org.eclipse.sirius.server.cors.enabled``` - The constant used to determine if Cross Origin Resource Sharing is enabled or not. Expects a boolean.
* ```org.eclipse.sirius.server.https.enabled``` - The constant used to determine whether SSL is activated or not.
* ```org.eclipse.sirius.server.https.host``` - The constant used to customize the HTTPS host.
* ```org.eclipse.sirius.server.https.port``` - The constant used to customize the HTTPS port.
* ```org.eclipse.sirius.server.ssl.keystore.path``` - The constant used to give the location of the key store for SSL authentication.
* ```org.eclipse.sirius.server.ssl.keystore.passphrase``` - The constant used to give the pass phrase for SSL authentication.
* ```org.eclipse.sirius.server.http.host``` - The constant used to customize the HTTP host.
* ```org.eclipse.sirius.server.http.port``` - The constant used to customize the HTTP port
* ```org.eclipse.sirius.services.graphql.cost``` - The constant used to customize the maximum cost of a GraphQL query (default: 100).

