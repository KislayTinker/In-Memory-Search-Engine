# In-Memory Search Engine (Java)

A lightweight **In-Memory Search Engine** implemented in **Java**, using core data structures like **HashMap**, **ArrayList**, and **Sets**.
This project demonstrates how real-world search engines work internally using **Tokenization**, **Inverted Indexing**, and **Top-K ranking**.

---

## 📌 Features

* Tokenization with stop-word removal
* In-memory inverted index
* Document-wise term frequency tracking
* Keyword-based search
* **Top-K search results** with scoring
* Fast lookup using Java HashMaps
* Pure **Core Java** implementation

---

## 🛠️ Technologies Used

* Java (JDK 8+)
* Core Java Collections Framework
* Object-Oriented Programming (OOP)

---

## 📂 Project Structure

```
In-Memory-Search-Engine/
│
├── Tokenizer.java
├── InvertedIndex.java
├── searching.java
│   └── Result (inner class)
├── Main.java
│
├── GraphMatrix.java
├── directedGraph.java
├── graghDir.java
├── Edge.java
│
└── SearchEngine.jar
```

> ⚠️ **Note:** Graph-related classes are included for demonstration purposes and are **not part of the core search engine logic**.

---

## 🚀 How the Search Engine Works

### 1️⃣ Tokenization

* Input text is split into individual tokens (words)
* Common stop words such as *is, the, and* are removed

### 2️⃣ Inverted Index Creation

* Each token maps to documents in which it appears
* Frequency of each token per document is stored

```
word → { documentId → frequency }
```

### 3️⃣ Query Processing

* User query is tokenized
* Matching documents are retrieved from the inverted index

### 4️⃣ Ranking (Top-K Search)

* Documents are scored based on term frequency
* Results are sorted by score
* Only the top **K** most relevant documents are returned

---

## 🧪 Example Usage

### Adding Documents

```java
searching engine = new searching();

engine.addDocument(1, engine.tokenizer.tokenize("Java is a powerful programming language"));
engine.addDocument(2, engine.tokenizer.tokenize("Java is widely used for backend development"));
engine.addDocument(3, engine.tokenizer.tokenize("Python and Java are popular languages"));
```

### Searching

```java
engine.topKSearch("java backend", 2);
```

### Sample Output

```
Document ID: 2 | Score: 2
Document ID: 1 | Score: 1
```

---

## ▶️ Running the Project

### Prerequisites

* Java installed (JDK 8 or higher)

Check Java installation:

```bash
java -version
```

### Run Using JAR File

```bash
java -jar SearchEngine.jar
```

---

## 📊 Core Classes Overview

### 🔹 `Tokenizer`

* Splits text into tokens
* Removes stop words

### 🔹 `InvertedIndex`

* Stores token → document mappings
* Enables fast retrieval of documents

### 🔹 `searching`

* Main search engine controller
* Adds documents to index
* Executes Top-K search
* Contains `Result` inner class

---

## 🎯 Learning Outcomes

* Understand internal working of search engines
* Learn inverted indexing technique
* Apply Java collections effectively
* Implement ranking algorithms
* Design scalable in-memory systems

---

## 🔮 Future Enhancements

* TF-IDF based scoring
* Phrase-based search
* Boolean queries (AND / OR / NOT)
* Disk-based index persistence
* REST API for querying
* Web-based search interface

---

## 📜 License

This project is open-source and intended for **learning and educational purposes**.

You are free to use, modify, and distribute this project.

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a new branch (`feature-branch`)
3. Commit your changes
4. Open a Pull Request

---

## ⭐ Support

If you find this project helpful:

* ⭐ Star the repository
* 🍴 Fork it
* 🧑‍💻 Share it with others

Happy Coding 🚀
