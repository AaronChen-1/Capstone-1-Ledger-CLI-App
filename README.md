# 🏪 Corner Mart Financial Tracker

**A Java console app for small-business bookkeeping**

---

## Overview

**Corner Mart Financial Tracker** is a console-based Java application that helps a small store track daily sales, deposits, and expenses.  
It uses a menu-driven interface and stores transactions in a CSV file so data persists between runs.

---

## Features

- **Add Deposits** — Record income (sales, bank deposits).
- **Make Payments** — Record expenses (inventory, utilities, etc.).
- **View Ledger** — See All Entries, Deposits only, or Payments only.
- **Reports** — Generate:
  - Month-to-Date
  - Previous Month
  - Year-to-Date
  - Previous Year
  - Search by Vendor
- **Smart Cancel** — Type `X` at any prompt to cancel and return to the Home screen.
- **Simulated Loading** — Small loading animation between screens for better UX.

---

## Application Screens


-  Welcome screen with loading animation
<img width="362" height="185" alt="image" src="https://github.com/user-attachments/assets/f0910dc0-0042-4996-9480-13b8694b6490" />


- Home menu
<img width="419" height="172" alt="image" src="https://github.com/user-attachments/assets/5a8f9073-15ae-4527-8999-9bcbe9311e50" />

- Ledger screen (All / Deposits / Payments)
<img width="414" height="194" alt="image" src="https://github.com/user-attachments/assets/dee9af48-4fe3-4318-bbf8-bd4c8198b19d" />

- Reports menu and example output
<img width="413" height="223" alt="image" src="https://github.com/user-attachments/assets/3d463f4e-ae3e-4cb6-9fac-794dd3ff62ae" />


## Interesting piece of code
```java
        System.out.println("(Type 'X' if you want to cancel and return to Home)" );

        System.out.print("Description: ");
        String description = scanner.nextLine();
        if (description.equalsIgnoreCase("X")) {
            simulateLoading("Deposit cancelled. Returning to Home...");
            return;
        }
```
