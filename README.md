# EnesKaanAfacan Student Portfolio

Modern, AI-assisted student portfolio website built with Java Spring Boot, Thymeleaf, and Bootstrap 5.

## 🚀 Features

- **Modern UI**: Glassmorphism, Neon Glow effects, and smooth animations.
- **Dynamic Content**: Blog posts from Markdown files, Random Quotes service.
- **Contact Form**: Integrated with Google Firebase (Firestore) and Email (JavaMailSender).
- **Security**: Basic rate limiting and environment variable configuration.

## 🛠️ Setup & Installation

### 1. Prerequisites
- Java 17 or higher
- Maven
- Google Firebase Project (for Firestore)
- Gmail Account (for SMTP)

### 2. Clone the Repository
```bash
git clone https://github.com/eneskaanafacan/StudentPortfolio.git
cd StudentPortfolio
```

### 3. Environment Configuration
This project uses environment variables for sensitive data. You can set them in your IDE or terminal.

**Required Environment Variables:**
- `MAIL_USERNAME`: Your Gmail address (e.g., `yourname@gmail.com`)
- `MAIL_PASSWORD`: Your Gmail App Password (NOT your regular password)

**Example (Linux/Mac):**
```bash
export MAIL_USERNAME="yourname@gmail.com"
export MAIL_PASSWORD="your_app_password"
```

### 4. Firebase Configuration
1. Go to your Firebase Console -> Project Settings -> Service Accounts.
2. Generate a new private key.
3. Rename the downloaded JSON file to `firebase-service-account.json`.
4. Place it in the **root directory** of the project (same level as `pom.xml`).

> **IMPORTANT:** Do NOT commit `firebase-service-account.json` to GitHub! It is already added to `.gitignore`.

### 5. Run the Application
```bash
mvn spring-boot:run
```
Access the application at `http://localhost:8080`.

## 🔒 Security Notes
- **Dependencies**: Checked for major vulnerabilities.
- **Input Handling**: Basic rate limiting is implemented on the contact form.
- **Secrets**: Credentials are managed via environment variables.

## 📝 License
This project is open source and available under the [MIT License](LICENSE).
