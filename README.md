# 🛡️ Insurance Broker - Android (Jetpack Compose)

A modern, declarative Android application designed to simplify the insurance discovery process. This project was developed to master **Jetpack Compose**, focusing on state management, reactive UI components.
---

## 🚀 Technical Highlights

As a software engineering project, the focus is on a robust UI layer and efficient state handling:

* **UI Framework:** 100% Jetpack Compose.
* **State Management:** State hoisting and `MutableStateFlow` to handle complex user inputs (sliders, switches, and dropdowns).
* **Navigation:** Compose Navigation for a seamless flow between input screens and results.

---

## 📸 Visual Walkthrough & Features

Below is the step-by-step user journey through the application.

### 1. Home Screen - Insurance Type Selection
The entry point where users select their specific insurance vertical.
> [!NOTE]
> <img width="326" height="724" alt="Screenshot 2026-03-24 at 16 46 58" src="https://github.com/user-attachments/assets/efd43aa7-8ca9-4a9e-821f-ebb38f700fb8" />

>
> **Description:** Users choose between Medical Aid, Life, or Vehicle insurance. This screen demonstrates the use of high-level navigation and buttons.

---

### 2. Medical Aid Configuration
A data-entry screen for specific health coverage needs.
> [!NOTE]
> <img width="331" alt="Medical Selection" src="https://github.com/user-attachments/assets/a05fc350-cd21-4c33-8bfc-e4b7abe28b31" />
>
> **Description:** User enters the number of dependents and monthly income. It features selection logic for plan types: **Hospital**, **Comprehensive**, and **Savings**.

---

### 3. Life Insurance Input
A high-interaction screen managing risk profiles and coverage limits.
> [!NOTE]
> <img width="328" alt="Life Insurance" src="https://github.com/user-attachments/assets/3a55aa6c-87ea-4ac2-87fd-f9ee83c36222" />
>
> **Description:** > * **Sliding Scale:** Interactive `Slider` to determine cover between **500k and R10m**.
> * **Risk Switches:** Toggle switches for "Smoker Status" and "Dangerous Activities."
> * **Occupation Selection:** An `ExposedDropdownMenu` for categorizing user occupation.

---

### 4. Candidate Insurer Results
The output screen displaying tailored insurance options.
> [!NOTE]
> <img width="328" alt="Insurers List" src="https://github.com/user-attachments/assets/8498d67f-203f-4a5f-9dd7-23b4aba94fd4" /> <br>
> **Description:** Based on the logic processed from the previous inputs, a list of candidate insurers is presented in a `LazyColumn`.

---

## 🛠️ Key Implementation Details

### State Hoisting & Reactive UI
In this project, I prioritized **State Hoisting**. For example, the Life Insurance slider value is hoisted to the ViewModel, ensuring that the UI remains a "dumb" reflection of the underlying data state:
