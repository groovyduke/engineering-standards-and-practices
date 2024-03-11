# XML vs Property files vs JSON vs Yaml vs Groovy vs TOML vs a Config Server

There are many different configuration file formats, some are easier to read and maintain that others. 

Out of the various formats in the title I prefer Groovy or TOML because they  have simple easy to read syntax that is relatively close to Groovy code.

Why I wouldn't suggest the others:
* XML is a bit verbose with opening and closing tags, and attributes
* Property files are ok but a bit limiting when it comes to things like list and maps. You also usually have to repeat part of keys.
* JSON isn't bad, but you have a lot of extra quotes.
* Yaml is like JSON with fewer quotes, but has significant white space and a funky list syntax.

That being said depending on what you're using the config for you might not have a choice. Yaml for instance is very popular and used for
Docker, K8 and many servers.

This brings us to the config server, which can be good if you're running a service/process that can pull config from a central service.
This can make it, so you can have config and secrets in one central location and not have to configure things  on a per-instance basis.
Also, a lot of config servers do have a way to filter for environment, you just pull down what you need per environment. When I've set this
up at various employers I would usually set up a global config that I could pull down and an environment specific config, so I didn't have
to copy all the global config for each environment.

The drawbacks to having a config server are that you will have to populate it, and pull that config into your app/process. Usually the population
will have to be in one of the config file formats depending on what you use Azure, AWS, GCP, Consul, Some DB, NoSQL DB, etc. Also, it will 
generally cost more because you have to have a server or service running for the config.