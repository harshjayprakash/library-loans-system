import toml

data = toml.load("project.toml")
data["project"]["version"]["build"] += 1
file = open("project.toml", 'w')
toml.dump(data, file)
file.close()