# DayTab

A tiny Bukkit/Spigot plugin that shows the world day count in the player's tab header/footer.

Build with Maven and drop the generated JAR into your server's `plugins/` folder.

Quick build:

```bash
mvn -f "./daytab-plugin/pom.xml" clean package
```

Then copy `daytab-plugin/target/daytab-1.0.0.jar` to your server's `plugins/`.

Notes:
- Targets Spigot API 1.21.1-R0.1-SNAPSHOT (change in `pom.xml` if you need a different version).
- Uses Java 17 compilation target; ensure your JDK matches.

License & AI disclaimer:
- This project is released under CC0 1.0 Universal (public domain). See `LICENSE` for details.
- Portions of this repository were assisted by an AI tool. See `AI_DISCLAIMER.md` for details and verification guidance.
