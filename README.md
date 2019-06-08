# NBT-Lib
## How to add to your project

Just add this repository and dependency to your project's pom and you are good to go:
```xml
<project>
    ...
    <repositories>
        ...
        <repository>
            <id>sytm-nexus</id>
            <url>http://sytm.de:8081/repository/maven-releases/</url>
        </repository>
        ...
    </repositories>
    ...
    <dependencies>
        <dependency>
            <groupId>de.md5lukas</groupId>
            <artifactId>nbt</artifactId>
            <version>1.1.0</version>
        </dependency>
    </dependencies>
    ...
</project>
```
* * *
## Basic usage
Every nbt structure has a root `CompoundTag`. Every value you want to store is a child of that root tag.
If you want you can name the root CompoundTag. Its somewhat comparable to a file, where the name of the tag is the name of the file and the childs of the tag the file's contents
```java
CompoundTag root = new CompoundTag("root");
```

To write the contents of the CompoundTag to a file, you can use the class `NbtIo` to do that. With that class you can write to an file in an uncompressed format or compress the output via `gzip`.
```java
// For uncompressed output:
NbtIo.write(root, file);
// For compressed output:
NbtIo.writeCompressed(root, file);
```

And to read the data back from the disk into memory just use the `read()` methods:
```java
// For uncompressed input:
CompoundTag root = NbtIo.read(file);
// For compressed input:
CompoundTag root = NbtIo.readCompressed(file);
```

Setting and retrieving values is pretty self explanatory, but as an example I am gonna show it using Strings:
```java
root.putString("key", "value");
// You can also do it like this:
root.put("key", new StringTag("key", "value"));
// Or even omit the the first parameter on tag creation, as CompoundTag.put sets the name also in the tag
root.put("key", new StringTag(null, "value"));
```
And to get the string again do this:
```java
String value = root.getString("key");
// or
String value = ((StringTag) root.get("key")).data;
```
For other types this works the same way.<br>
**IMPORTANT:** The tags found in the package `de.md5lukas.nbt.extended` are custom tags which normal nbt software can't understand and because of this they first need to be registered like this:
```java
Tags.registerExtendedTags();
```
This will enable this library to write and read these special tags. If this step is not done, `InvalidTagException` will be thrown