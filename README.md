# LED Project

Ein Java-basiertes Space-Invaders-ähnliches Spiel, entwickelt für LED-Matrix-Anzeigen mit dem BoardController-Framework.

## 📋 Übersicht

Dieses Projekt implementiert ein interaktives Spiel auf einer LED-Matrix, bei dem der Spieler gegen computergesteuerte Gegner kämpft. Das Spiel bietet Bewegungssteuerung, Schussmechaniken und visuelle Feedback-Systeme über farbige LEDs.

## ✨ Features

- **Spieler-Steuerung**: Horizontale Bewegung mit Echtzeit-LED-Feedback
- **Gegner-System**: KI-gesteuerte Gegner mit automatischer Bewegung
- **Kampfsystem**: Schussmechanik für Spieler und Gegner
- **Lebenssystem**: Gesundheitstracking mit visuellen Indikatoren
- **End-Screens**: Sieg- und Niederlage-Animationen
- **Farbcodierung**: Unterschiedliche Farben für verschiedene Spielelemente

## 🎮 Spielmechanik

### Steuerung
- **Horizontale Bewegung**: Links/Rechts-Navigation
- **Schießen**: Projektile auf Gegner abfeuern
- **Kollisionserkennung**: Automatische Hit-Detection

### Spielelemente
- 🟢 **Spieler**: Grün (wird rot bei niedrigem Leben)
- 🔴 **Gegner**: Rot
- 🟢 **Spieler-Geschosse**: Grün
- 🟣 **Gegner-Geschosse**: Lila

## 🏗️ Projektstruktur

```
led_project/
├── src/
│   ├── Animation.java          # Basis-Animationsklasse
│   ├── Animations.java         # Interface für animierte Objekte
│   ├── Player.java             # Spielerlogik und -steuerung
│   ├── Enemy.java              # Gegnerverhalten
│   ├── Bullet.java             # Projektilverwaltung
│   ├── Position.java           # Positionsmanagement
│   ├── ColorHolder.java        # Farbdefinitionen
│   ├── EndScreen.java          # Endbildschirm-Grafiken
│   └── RandomNumberGenerator.java  # Zufallszahlengenerierung
├── bin/                        # Kompilierte Klassen
└── LEDControl_v2_r8.jar       # LED-Controller-Bibliothek
```

## 🔧 Technische Details

### Hauptklassen

#### `Animation.java`
Basis-Animationsklasse mit: 
- Positionsverwaltung
- Bewegungsrichtungen
- Schussmechanik
- Bullet-Verwaltung

#### `Player.java`
Erweitert `Animation` und implementiert:
- 5 Lebenspunkte (Standard)
- Horizontale Bewegung mit Grenzerkennung
- Visuelles Feedback bei niedrigem Leben
- Synchronisierte Bullet-Bewegung

#### `Enemy.java`
Gegnerklasse mit:
- Automatischer horizontaler Bewegung
- 4-LED-Darstellung (3 horizontal, 1 vertikal)
- Kollisionserkennung

#### `Bullet.java`
Projektilverwaltung: 
- Aufwärts-/Abwärtsbewegung
- Hit-Detection für Spieler und Gegner
- Farbdifferenzierung nach Typ

#### `EndScreen.java`
Endbildschirme:
- **Sieg**: Lächelndes Gesicht
- **Niederlage**: Totenkopf-Symbol

### Farben

```java
public static int[] red = {127,0,0};      // Gegner
public static int[] green = {0,127,0};    // Spieler
public static int[] blue = {0,0,127};     // Nicht verwendet
public static int[] purple = {127,0,127}; // Gegner-Geschosse
public static int[] yellow = {127,127,0}; // Nicht verwendet
public static int[] white = {127,127,127}; // End-Screens
```

## 🚀 Installation

### Voraussetzungen
- Java Development Kit (JDK) 8 oder höher
- LED-Matrix-Hardware mit BoardController-Unterstützung
- `LEDControl_v2_r8.jar` Bibliothek

### Setup

1. Repository klonen:
```bash
git clone https://github.com/spvapech/led_project.git
cd led_project
```

2. Projekt kompilieren:
```bash
javac -cp LEDControl_v2_r8.jar:. src/*.java -d bin/
```

3. Spiel starten:
```bash
java -cp LEDControl_v2_r8.jar:bin Main
```

## 🎯 Verwendung

### Spiel initialisieren

```java
BoardController controller = new BoardController();
Player player = new Player(controller, startX, startY, bullet);
Enemy enemy = new Enemy(controller, startX, startY, bullet);
```

### Spieler bewegen

```java
player.setDirectionHorizontal((byte)0); // Links
player.setDirectionHorizontal((byte)1); // Rechts
player.moveHorizontally();
```

### Schießen

```java
player.shoot();
bullet.move(true); // true = Spieler-Bullet
bullet.draw(true);
```

## 🔄 Spielablauf

1. **Initialisierung**: Spieler und Gegner werden auf der LED-Matrix platziert
2. **Game Loop**:
   - Eingaben verarbeiten
   - Positionen aktualisieren
   - Kollisionen prüfen
   - LED-Matrix rendern
3. **Spielende**:  Sieg- oder Niederlage-Screen anzeigen

## 📊 Spielmechaniken

### Lebenssystem
- Spieler startet mit 5 Leben
- Bei 1 Leben verbleibt wird der Spieler rot
- Gegner-Treffer reduzieren Leben um 1

### Kollisionssystem
- Bullet-to-Enemy Kollision
- Bullet-to-Player Kollision
- Grenzerkennung für Bewegungen

## 🤝 Mitwirken

Beiträge sind willkommen!  Bitte erstelle einen Pull Request oder öffne ein Issue für Vorschläge.

## 📝 Lizenz

Dieses Projekt hat derzeit keine Lizenz. Bitte kontaktiere den Repository-Owner für Nutzungsinformationen.

## 👤 Autor

**spvapech**
- GitHub: [@spvapech](https://github.com/spvapech)

## 🔗 Ressourcen

- [Repository](https://github.com/spvapech/led_project)
- BoardController Dokumentation (LEDControl_v2_r8.jar)

## 📅 Version History

- **v1.0** (Dezember 2023): Erste Veröffentlichung
  - Grundlegende Spielmechanik
  - Spieler- und Gegner-System
  - End-Screens
  - Kollisionserkennung

---

**Hinweis**: Dieses Projekt benötigt spezielle LED-Matrix-Hardware und die BoardController-Bibliothek zur Ausführung.