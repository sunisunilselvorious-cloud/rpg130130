# RPG130130

📌 Descrizione

Questo progetto consiste nello sviluppo di un'applicazione Java realizzata per il corso "Metodologie di Programmazione – Modellazione e Gestione della Conoscenza".

L’applicazione implementa un semplice Gioco di Ruolo (RPG) dotato di interfaccia grafica JavaFX, sistema di combattimento, gestione dell’inventario 
e persistenza dei dati in formato JSON.

---

🎮 Funzionalità principali

L’applicazione offre le seguenti funzionalità:

Interfaccia grafica sviluppata con JavaFX
Sistema di combattimento tra Player ed Enemy
Gestione dei punti vita (HP) con barra grafica
Sistema di inventario con bonus agli attacchi
Salvataggio e caricamento dello stato del giocatore
Possibilità di iniziare una nuova partita (New Game)

---

💾 Persistenza dei dati

La persistenza è gestita tramite file JSON (save.json) utilizzando la libreria Gson.

Vengono salvati i principali attributi del Player:

Nome
Punti vita (HP)
Attacco

---

🧱 Struttura del progetto

Il progetto è organizzato nei seguenti package:

combat → logica di combattimento
item → gestione inventario e oggetti
model → entità principali (Player, Enemy)
persistence → gestione del salvataggio/caricamento
ui → interfaccia grafica

---

🧠 Principi di progettazione

Il progetto segue i principali principi SOLID:

Single Responsibility → ogni classe ha una responsabilità ben definita
Open/Closed → il sistema è estendibile senza modificare il codice esistente
Dependency Inversion (parziale) → utilizzo dell’interfaccia GameRepository per la gestione della persistenza

---

🚀 Estendibilità

L’applicazione è progettata per supportare future estensioni, tra cui:

Introduzione di nuovi nemici
Aggiunta di nuovi oggetti nell’inventario
Estensione del sistema di combattimento
Possibile evoluzione verso versioni web o mobile
⚙️ Tecnologie utilizzate
Java 21
JavaFX
Gradle
Gson (per la gestione della persistenza JSON)

---

▶️ Esecuzione del progetto

Per compilare ed eseguire il progetto utilizzare i seguenti comandi:

./gradlew build
./gradlew run

---

🤖 Uso dell’AI

Durante lo sviluppo sono stati utilizzati strumenti di AI (come ChatGPT) per:

Supporto nella progettazione dell’architettura
Suggerimenti sui principi SOLID e Clean Code
Debug e miglioramento del codice
Supporto nella scrittura della documentazione

---

👨‍💻 Autore

Progetto sviluppato da:

Studente: Sunisha Sunil Selvorious
Matricola: 130130
