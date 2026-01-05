# In-Memory Search Engine (Java)

A lightweight, pure Java search engine that works entirely in memory using core data structures like HashMap, ArrayList, and Set.
This project demonstrates core search concepts such as tokenization, inverted indexing, and Top-K ranking — exactly how search engines work under the hood.

---

🚀 Features

✔ Tokenization with stop-word removal
✔ In-memory inverted index
✔ Document-level term frequency storage
✔ Keyword-based search
✔ Top-K ranked results
✔ Fast lookups using Java Collections
✔ 100% Core Java (no external libs)

---

🛠️ Tech Stack

Technology | Description
-----------|------------
Java (JDK 8+) | Language
Core Collections | HashMap, ArrayList, Set
OOP | Object-oriented design

---

📂 Project Structure

In-Memory-Search-Engine/

│

├── Tokenizer.java

├── InvertedIndex.java

├── searching.java

│   └── Result (inner class)

├── Main.java

│

└── SearchEngineProject.jar

> ⚠️ Graph-related classes are for demonstration and not part of the core search engine logic.

---

🔍 How It Works

1. Tokenization

- Break input text into word tokens
- Remove common stop words like is, the, and, etc.

---

2. Inverted Index

Each token gets mapped to the documents it appears in, along with its frequency:

word → { documentId → frequency }

This enables fast retrieval of matching documents.

---

3. Query Processing

- Queries are tokenized just like documents
- Each keyword retrieves matching document lists from the inverted index

---

4. Ranking (Top-K Search)

Documents are scored by term frequency, sorted descending, and the top K results are returned.

---

🧪 Example Usage

📄 Adding Documents

searching engine = new searching();

engine.addDocument(1, engine.tokenizer.tokenize("Java is a powerful programming language"));
engine.addDocument(2, engine.tokenizer.tokenize("Java is widely used for backend development"));
engine.addDocument(3, engine.tokenizer.tokenize("Python and Java are popular languages"));

---

🔎 Performing a Search

engine.topKSearch("java backend", 2);

🖨️ Sample Output

Document ID: 2 | Score: 2
Document ID: 1 | Score: 1

---

▶️ Running the Project

📌 Prerequisites

Make sure Java (JDK 8 or higher) is installed:

java -version

---

🚀 Run with JAR

java -jar SearchEngineProject.jar

---

📌 Core Classes Overview

🔹 Tokenizer

- Splits text into tokens
- Eliminates stop words

---

🔹 InvertedIndex

- Maintains token → document mappings
- Efficient document lookup

---

🔹 searching

- Orchestrates indexing & searching
- Implements Top-K ranking
- Contains the Result inner class

---

📈 Learning Outcomes

After exploring this project, you’ll understand:
✔ How search engines tokenize and index text
✔ Inverted index structures
✔ How ranking works (term frequency)
✔ Using Java Collections effectively
✔ Designing scalable in-memory systems

---

💡 Future Enhancements

Here are some cool upgrades you could add:

📌 Phrase-based search
📌 Disk-based persistence
📌 REST API interface
📌 Web UI for querying

---

📜 License

This project is open-source and intended for learning & educational purposes.
Feel free to modify, improve, and share!

---

🤝 Contributing

We welcome contributions!

1. Fork the repository
2. Create a new branch (feature-branch)
3. Commit your changes
4. Open a Pull Request

---

⭐ Support

If you find this project helpful:

⭐ Star the repo
🍴 Fork it
🧑‍💻 Share it with others

Happy Coding! 🚀
