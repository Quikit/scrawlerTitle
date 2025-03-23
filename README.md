To get started with the project, you need to follow these commands:

1. Compile the project:
   ```bash
   sbt compile
   ```

2. Run the project:
   ```bash
   sbt run
   ```

## Positive Test Case

To test the application, you can make a request to the following URL:

```
GET http://localhost:9000/
```

### Request Body

You should send the following JSON body in your request:

```json
{
    "urls": ["https://www.google.com", "https://docs.scala-lang.org"]
}
```

### Expected Result

```json
[
    {
        "url": "https://www.google.com",
        "title": "Google"
    },
    {
        "url": "https://docs.scala-lang.org",
        "title": "Learn Scala | Scala Documentation"
    }
]
```

## Negative Test Case

```
GET http://localhost:9000/
```

### Request Body

```json
{
    "urls": "Not Array"
}
```

### Expected Result

```json
{
    "message": "Invalid JSON structure: 'urls' must be an array of strings"
}
```
