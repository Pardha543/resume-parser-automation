# Resume Parser Automation (Java + n8n + API Integrations)

An automation/integration project that demonstrates a real workflow recruiters rarely see:
**Resume Upload → Java parses resume → n8n automates Google Sheets logging + Email alerts**.

## What it does

1. User uploads a resume (PDF/DOCX).
2. Java (Spring Boot) extracts text from the resume.
3. Java parses the resume into structured JSON (AI-ready).
4. Java sends the structured JSON to **n8n webhook**.
5. n8n:
   - Appends a row in **Google Sheets**
   - Sends an **email alert** (to HR/candidate)
   - (Optional) Sends Slack/Teams notification

## Tech Stack

- **Java 17**, **Spring Boot**
- **n8n** (self-hosted via Docker)
- **Google Sheets API** (via n8n connector)
- **Email (SMTP)** (via n8n)
- Optional: **OpenAI / Gemini** for AI parsing

## Repository Structure

resume-parser-automation/
backend/ # Spring Boot API
n8n/ # n8n workflow JSON exports
docs/ # screenshots + architecture
README.md
