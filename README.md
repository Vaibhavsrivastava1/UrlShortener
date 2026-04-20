# 🔗 URL Shortener (Spring Boot)

A scalable and secure URL Shortener built using Spring Boot.  
This project allows users to create short links, track usage analytics, and manage their own URLs with authentication.

---

## 🚀 Features

- 🔐 JWT Authentication (Login & Signup)
- 🔗 Short URL generation (Base62 encoding)
- 👤 User-specific URL management
- 📊 Analytics (click tracking, top URLs)
- ✏️ Custom short URL support
- 📄 Pagination for user URLs
- ⏳ URL Expiration support
- ⚡ RESTful API design
- 🛡️ Global Exception Handling

---

## 🧠 How It Works

1. User logs in and receives a JWT token  
2. User sends a long URL → API generates a short code  
3. Short URL redirects to original URL  
4. Each visit is tracked for analytics  

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot  
- **Security:** Spring Security + JWT  
- **Database:** MySQL  
- **ORM:** Hibernate / JPA  
- **Build Tool:** Maven  

---

## 📌 API Endpoints

### 🔐 Authentication
- `POST /auth/signup`
- `POST /auth/login`
- Authorization: Bearer <your_token>

### 🔗 URL Management
- `POST /shorten` → Create short URL  
- `GET /{code}` → Redirect to original URL  
- `GET /my-urls` → Get user URLs (paginated)  

### 📊 Analytics
- `GET /analytics/top?limit=5`

---

## 🔑 Authentication Usage

Include JWT token in header:

---

## ⚙️ Setup & Run

1. Clone the repository:https://github.com/Vaibhavsrivastava1/UrlShortener
2. Configure database in `application.properties`
3. Run the application:
   
---

## 🌐 Deployment

> Deployment in progress / Coming soon

---

## 💡 Future Improvements

- Redis caching  
- Rate limiting  
- QR code generation  
- Custom domain support  

---

## 🙌 Author

**Vaibhav Srivastava**

---

## ⭐ If you like this project

Give it a star ⭐ on GitHub!
