# AI Resume Automation Pipeline 🚀

This project demonstrates an **automated resume processing pipeline** built using **Spring Boot, n8n, and Google Sheets**.

The system automatically receives candidate resume data via a webhook, processes the information, and stores structured candidate details in **Google Sheets** for further analysis.

---

# 📌 Features

• Automated Resume Data Processing
• Webhook-based Integration
• Google Sheets Candidate Database
• No-code Automation using n8n
• Scalable Architecture

---

# 🏗️ Architecture

```
Spring Boot Resume Parser
          │
          ▼
       Webhook
          │
          ▼
       n8n Workflow
          │
          ▼
    Google Sheets Database
```

---

# ⚙️ Tech Stack

Backend
• Java
• Spring Boot

Automation
• n8n Workflow Automation

Data Storage
• Google Sheets API

Cloud / Integration
• Google Cloud Platform

---

# 📂 Project Workflow

1. Resume data is sent to the **Webhook endpoint**.
2. n8n receives the payload.
3. Fields are mapped and processed.
4. Candidate information is appended to **Google Sheets**.

Stored fields:

• Timestamp
• Candidate Name
• Email
• Phone
• Education
• Skills

---

# 📊 Example Output

| Timestamp  | Name           | Email                                                     | Phone  | Skills                   |
| ---------- | -------------- | --------------------------------------------------------- | ------ | ------------------------ |
| 2026-03-04 | Pardha Saradhi | [pardhasaradhi@gmail.com](mailto:pardhasaradhi@gmail.com) | +44... | Java, Spring Boot, React |

---

# 🚀 How to Run

### 1️⃣ Start n8n

```
npx n8n
```

or

```
docker run -it --rm \
-p 5678:5678 \
n8nio/n8n
```

---

### 2️⃣ Configure Google Sheets

1. Enable APIs
   - Google Sheets API
   - Google Drive API

2. Create **Service Account**

3. Download credentials JSON

4. Share Google Sheet with service account email.

---

### 3️⃣ Import Workflow

Import the n8n workflow JSON file.

---

# 📌 Future Improvements

• AI Resume Ranking using LLM
• Candidate Shortlisting Dashboard
• Resume PDF Upload Support
• Automated Email Notifications

---

# 👨‍💻 Author

**Pardha Saradhi Chakolthi**

MSc Computer Science
University of East London

GitHub:
https://github.com/

---

# ⭐ If you like this project

Give it a ⭐ on GitHub!
