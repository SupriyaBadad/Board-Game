public class Board {
    private int gridRows;
    private int gridCols;
    private int[][] currentGrid;
    private int generation = 0;
    
    public Board(int r, int c) {
        gridRows = r;
        gridCols = c;
        generation = 0;
        currentGrid = new int[r][c];
    }
    
    public int[][] getCurrentGrid() {
        return currentGrid;
    }
    
    public void setCurrentGrid(int[][] grid) {
        currentGrid = grid;
    }
    
    public int getGeneration() {
        return generation;
    }
    
    public void setGeneration(int generation) {
        generation = generation;
    }
    
    public void seedCurrentGeneration() {
        int[][] grid = getCurrentGrid();
           
        if(getGeneration() == 0) {
            grid[1][3] = 1;
            grid[1][4] = 1;
            grid[2][4] = 1;
            grid[5][3] = 1;
            grid[5][4] = 1;
            grid[6][2] = 1;
            grid[6][3] = 1;
            grid[7][5] = 1;
            grid[8][4] = 1;
        }
        setCurrentGrid(grid);
    }
    
    public void getNextGenerations(int generations) {
        for(int g=1; g <= generations; g++) {
            getNextGeneration(getCurrentGrid());
        }
    }
    
    public void getNextGeneration(int[][] currentGrid) {
        this.generation++;
        this.setGeneration(this.getGeneration()+1);
        int[][] nextGen = new int[gridRows][gridCols];
        for(int i = 0; i < gridRows; i++) {
            for(int j = 0; j < gridCols; j++) {
                int activeCount = countLiveNeighbours(i,j);
                //Add all 4 rules of Game of life 
                 if(currentGrid[i][j] == 1 && (activeCount == 2 || activeCount == 3)) {
                     setLiveStatus(i, j, nextGen);
                 } else if(currentGrid[i][j] ==1 && ( activeCount < 2 || activeCount > 3)) {
                     setDeadStatus(i, j, nextGen);
                 }  else if(currentGrid[i][j] == 0 && activeCount == 3) {
                     setLiveStatus(i, j, nextGen);
                 } 
            }
        }
        printGrid(nextGen);
        this.setCurrentGrid(nextGen);
    }
    
    public void printGrid(int[][] grid) {
        System.out.println("---------------------------------------------------------------------------");
        for(int i=0 ;i < gridRows ; i++) {
            StringBuilder rows = new StringBuilder("|");
           
            for(int j=0; j < gridCols; j++) {
                if(grid[i][j] == 1) {
                   rows.append(" * ");
                } else {
                    rows.append(" . ");
                }
            }
            rows.append("|");
            System.out.println(rows);
        }
        System.out.println("---------------------------------------------------------------------------\n");
    }
    
    private void setLiveStatus(int x, int y, int[][] nextGen) {
        nextGen[x][y]= 1;
    }
    
    private void setDeadStatus(int x, int y, int[][] nextGen) {
        nextGen[x][y]= 0;
    }
    
    private int countLiveNeighbours(int x, int y) {
        int liveCount = 0;
     
        liveCount += getCellValue(x-1, y-1);
        liveCount += getCellValue(x, y-1);
        liveCount += getCellValue(x+1, y-1);
        liveCount += getCellValue(x-1, y);
        liveCount += getCellValue(x+1, y);
        liveCount += getCellValue(x-1, y+1);
        liveCount += getCellValue(x, y+1);
        liveCount += getCellValue(x+1,y+1);
        
        return liveCount;
    }
    
    private int getCellValue(int x, int y) {
        if(x < 0  || x >= gridRows) {
            return 0;
        } 
        if(y < 0 || y >= gridCols ) {
            return 0;
        }
        return getCurrentGrid()[x][y];
    }
    
}