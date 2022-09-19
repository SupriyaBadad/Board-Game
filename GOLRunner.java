public class GOLRunner {
    public static void main(String args[]) {
        Board grid = new Board(25, 25);
        grid.seedCurrentGeneration();
        grid.printGrid(grid.getCurrentGrid());
        grid.getNextGenerations(3);
    }
}
