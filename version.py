"""
[THIS FILE IS NOT FOR MARKING]
This python file is for automatically incrementing the version number
in the project.toml file every time that maven compiles the java
application.
"""
error_occurred: bool = False

try:
    import toml
except ImportError:
    print("Failed to import toml library")
    error_occurred = True

PROJECT_TOML_FILE = "project.toml"

if not error_occurred:
    data = toml.load(PROJECT_TOML_FILE)
    data["project"]["version"]["build"] += 1
    with open(PROJECT_TOML_FILE, 'w') as file:
        toml.dump(data, file)
