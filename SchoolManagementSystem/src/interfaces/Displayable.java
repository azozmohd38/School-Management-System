package interfaces;

public interface Displayable {

    void displayInfo();

    void displaySummary();

    default void displayBoth() {
        displaySummary();
        displayInfo();
    }
}
