# Java Swing UI - Honkai: Star Rail Character Viewer

This project is a custom Java Swing application built with Gradle that simulates a character list interface similar to Honkai: Star Rail. It features a frameless, resizable, and draggable window layout using custom-built UI components.

## Features

* **Frameless Window**: The native OS window decoration is removed using `setUndecorated(true)`.
* **Custom Draggable Navbar**: The top `NavbarPanel` allows the user to drag and move the entire window.
* **Resizable Edges**: The edges and corners of the application window are resizable via custom mouse listeners.
* **Split Pane Layout**: The left-side `SidebarPanel` and right-side `CharacterGridPanel` are embedded inside a `JSplitPane`.
* **Placeholder Images**: Uses `https://placehold.co/` for character thumbnails.

## Technologies

* Java 17+
* Swing & AWT
* Gradle

## Project Structure

```
src/
  main/
    java/
      hsr_java_gui_gradle/
        App.java
        NavbarPanel.java
        SidebarPanel.java
        CharacterGridPanel.java
        CharacterCard.java
        ResizableFrameHelper.java
```

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/wyattmatt/Java-Swing-UI.git
cd Java-Swing-UI
```

2. Build and run with Gradle:

```bash
./gradlew build
./gradlew run
```

## Screenshots

> You can update this section with actual UI screenshots once the character images and design are finalized.

## License

This project is for educational and non-commercial use only.

---

Feel free to modify or extend the layout to include filtering, character detail pages, or dynamic loading in the future.
