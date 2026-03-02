# 🛴 Stellar Burgers — API Automation Tests (Praktikum)

---

## 🧰 Animated Tech Stack

<p align="center">
<img src="https://readme-typing-svg.demolab.com?font=Fira+Code&weight=600&size=28&duration=2500&pause=800&color=00C2FF&center=true&vCenter=true&width=900&lines=Java+11%2B;JUnit+4+API+Testing;REST+Assured+5.x;Allure+Reporting;Maven+Build+Automation" />
</p>


<p align="center">
<table>
<tr>

<td align="center" width="130">
<img src="https://skillicons.dev/icons?i=java" width="60"/><br>
<b>Java</b><br>11+
</td>

<td align="center" width="130">
<img src="https://skillicons.dev/icons?i=maven" width="60"/><br>
<b>Maven</b><br>3.8+
</td>

<td align="center" width="130">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/junit/junit-original.svg" width="60"/><br>
<b>JUnit</b><br>4
</td>

<td align="center" width="130">
<img src="https://raw.githubusercontent.com/rest-assured/rest-assured/master/logo.png" width="60"/><br>
<b>REST Assured</b><br>5.x
</td>

<td align="center" width="130">
<img src="https://raw.githubusercontent.com/allure-framework/allure2/master/.github/images/allure-logo.svg" width="60"/><br>
<b>Allure</b><br>Report
</td>

<td align="center" width="130">
<img src="https://skillicons.dev/icons?i=idea" width="60"/><br>
<b>IntelliJ IDEA</b><br>2023+
</td>

</tr>
</table>
</p>

---

## 📌 Project Description

Automated **API testing** project for **Stellar Burgers** service.

The project covers required endpoints from Praktikum task:
- **User creation**
- **User login**
- **Order creation**

✅ JUnit 4 + REST Assured for API tests  
✅ Allure for detailed reporting  
✅ Clean structure with steps / models / constants

---

## ✅ Task 2. API Autotests — Covered Scenarios

### 👤 User creation
- create a **unique** user
- create a user that is **already registered**
- create a user with a **missing required field**

### 🔐 User login
- login with an **existing** user
- login with **invalid** credentials (wrong email/password)

### 🧾 Order creation
- create order **with authorization**
- create order **without authorization**
- create order **with ingredients**
- create order **without ingredients**
- create order with **invalid ingredient hashes**

---

## 📁 Project Structure

```text
src
 ├── main/java
 │   ├── constants
 │   │   ├── Endpoints.java
 │   │   └── Urls.java
 │   └── model
 │       ├── order
 │       │   └── OrderCreate.java
 │       └── user
 │           ├── UserCreate.java
 │           └── UserLoginRequest.java
 │
 └── test/java
     ├── base
     ├── client
     ├── steps
     │   ├── OrderSteps.java
     │   └── UserSteps.java
     ├── testdata
     │   └── UserGenerator.java
     ├── user
     │   ├── UserCreateTest.java
     │   └── UserLoginTest.java
     └── order
         └── OrderCreateTest.java
````

---

## ▶️ How to Run Tests

```bash
mvn clean test
```

---

## 📊 Allure Report

Generate report:

```bash
mvn allure:report
```

Open report:

```bash
mvn allure:serve
```

---

## 🧼 Notes

* Tests create unique users to avoid collisions
* Where needed, requests are wrapped into **Steps** classes for readability
* Constants for base URL and endpoints are located in `constants/`

---

## 📌 Result

✅ Project is pushed to GitHub
✅ Branch: `develop2`
✅ Pull request created (develop2 → main)

---

## 👩‍💻 Author
Anna Korolchuk

QA Automation Educational Project — Praktikum



