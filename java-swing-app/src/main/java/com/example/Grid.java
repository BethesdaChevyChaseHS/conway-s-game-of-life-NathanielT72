package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel implements ActionListener {
    private int rows;
    private int cols;
    private int cellSize;
    private int[][] grid;  // 2D array representing the grid state

    public Grid(int rows, int cols, int cellSize, boolean randomStart, boolean customStart) {
        this.rows = rows;
        this.cols = cols;
        this.cellSize = cellSize;
        this.grid = new int[rows][cols];  // Initialize the grid with default state 0 (empty)
        
        setPreferredSize(new Dimension(cols * cellSize, rows * cellSize));
        if(customStart){
            for(int k = 0; k < rows; k++){
                for(int m = 0; m < cols; m++) {
                    if((k + m) % 2 == 0) {
                        this.grid[k][m] = 1;
                    }
                    else {
                        this.grid[k][m] = 0;
                    }
                    
                }
            }
        }else if(randomStart){
            //initialize grid to random values:
            for(int i = 0; i < rows;i++){
                for(int j = 0; j < cols;j++){
                    this.grid[i][j] = (int)(Math.random()*2);
                }
            }
        } else {
            //create a glider for testing
            this.grid[2][0] = 1;
            this.grid[3][1] = 1;
            this.grid[1][2] = 1;
            this.grid[2][2] = 1;
            this.grid[3][2] = 1;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Loop through each cell and paint based on alive or dead
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                
                if (grid[row][col] == 0) {//dead
                    g.setColor(Color.BLACK);
                } else if (grid[row][col] == 1) {
                    // Wall (brown)
                    g.setColor(Color.WHITE); // alive
                } else {
                    throw new Error("Not a valid grid value");
                }
                

                // Draw the cell
                g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);

                // Draw grid lines (optional)
                g.setColor(Color.BLACK);
                g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
    }

    //ALL YOUR CODE GOES HERE
    public void nextGeneration() {
        //1 Create a new temporary new array to store the values of the next generation
int[][]tempGrid = new int[rows][cols];
        //2 Visit every cell in the new temporary grid. Check the number of neighboring cells, and based on the rules determine whether the cell will be alive or dead.
        //Watch out for edge cases!
        for (int row = 0; row <= (tempGrid[0].length - 1); row++) {
            for (int col = 0; col < tempGrid.length; col++) {
                if((row == 0) && (col == 0)) {
                    int topLeft = 0;
                        if(grid[row + 1][col] == 1){
                            topLeft++;
                        }
                        if(grid[row + 1][col + 1] == 1) {
                            topLeft++;
                        }
                        if(grid[row][col + 1] == 1) {
                            topLeft++;
                        }
                        if(grid[row][col] == 1) {
                            if((topLeft == 2) || (topLeft == 3 )) {
                                tempGrid[row][col] = 1;
                            }
                            else{
                                tempGrid[row][col] = 0;
                            }
                        }
                        if(grid[row][col] == 0) {
                            if(topLeft == 3 ) {
                                tempGrid[row][col] = 1;
                            }
                            else{
                                tempGrid[row][col] = 0;
                            }
                        }
                }
            else if ((row == 0) && (col == (this.cols - 1))) {
                    int topRight = 0;
                        if(grid[row + 1][col] == 1){
                            topRight++;
                        }
                        if(grid[row + 1][col - 1] == 1) {
                            topRight++;
                        }
                        if(grid[row][col - 1] == 1) {
                            topRight++;
                        }
                        if(grid[row][col] == 1) {
                            if((topRight == 2) || (topRight == 3 )) {
                                tempGrid[row][col] = 1;
                            }
                            else{
                                tempGrid[row][col] = 0;
                            }
                        }
                        if(grid[row][col] == 0) {
                            if(topRight == 3 ) {
                                tempGrid[row][col] = 1;
                            }
                            else{
                                tempGrid[row][col] = 0;
                            }
                        }
                }
            else if ((row == (this.rows - 1)) && (col == 0)) {
                    int bottomLeft = 0;
                    if(grid[row - 1][col] == 1){
                        bottomLeft++;
                    }
                    if(grid[row - 1][col + 1] == 1) {
                        bottomLeft++;
                    }
                    if(grid[row][col + 1] == 1) {
                        bottomLeft++;
                    }
                    if(grid[row][col] == 1) {
                        if((bottomLeft == 2) || (bottomLeft == 3 )) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                    if(grid[row][col] == 0) {
                        if(bottomLeft == 3 ) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                }
            }
             else if ((row == (this.rows - 1)) && (col == (this.cols - 1))){
                    int bottomRight = 0;
                    if(grid[row - 1][col] == 1){
                        bottomRight++;
                    }
                    if(grid[row - 1][col - 1] == 1) {
                        bottomRight++;
                    }
                    if(grid[row][col - 1] == 1) {
                        bottomRight++;
                    }
                    if(grid[row][col] == 1) {
                        if((bottomRight == 2) || (bottomRight == 3 )) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                    if(grid[row][col] == 0) {
                        if(bottomRight == 3 ) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                }
            }
             else if(row == 0) {
                    int topRow = 0;
                    if(grid[row + 1][col] == 1){
                        topRow++;
                    }
                    if(grid[row + 1][col - 1] == 1) {
                        topRow++;
                    }
                    if(grid[row][col - 1] == 1) {
                        topRow++;
                    }
                    if(grid[row + 1][col + 1] == 1) {
                        topRow++;
                    }
                    if(grid[row][col + 1] == 1) {
                        topRow++;
                    }
                    if(grid[row][col] == 1) {
                        if((topRow == 2) || (topRow == 3 )) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                    if(grid[row][col] == 0) {
                        if(topRow == 3 ) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                }
            }
               
                else if(row == (this.rows - 1)) {
                    int bottomRow = 0;
                    if(grid[row - 1][col] == 1){
                        bottomRow++;
                    }
                    if(grid[row - 1][col - 1] == 1) {
                        bottomRow++;
                    }
                    if(grid[row][col - 1] == 1) {
                        bottomRow++;
                    }
                    if(grid[row - 1][col + 1] == 1) {
                        bottomRow++;
                    }
                    if(grid[row][col + 1] == 1) {
                        bottomRow++;
                    }
                    if(grid[row][col] == 1) {
                        if((bottomRow == 2) || (bottomRow == 3 )) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                    if(grid[row][col] == 0) {
                        if(bottomRow == 3 ) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                }
                else if(col ==(this.cols - 1)) {
                    int rightCol = 0;
                    if(grid[row + 1][col] == 1){
                        rightCol++;
                    }
                    if(grid[row + 1][col - 1] == 1) {
                        rightCol++;
                    }
                    if(grid[row - 1][col - 1] == 1) {
                        rightCol++;
                    }
                    if(grid[row][col - 1] == 1) {
                        rightCol++;
                    }
                    if(grid[row - 1][col] == 1) {
                        rightCol++;
                    }
                    if(grid[row][col] == 1) {
                        if((rightCol == 2) || (rightCol == 3 )) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid [row][col] = 0;
                        }
                    }
                    if(grid[row][col] == 0) {
                        if(rightCol == 3 ) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                }
                else if ((row != 0) && (row < (this.rows - 1)) && (col != 0) && (col < (this.cols - 1))) {
                    int middle = 0;
                    if(grid[row + 1][col] == 1){
                        middle++;
                    }
                    if(grid[row + 1][col - 1] == 1) {
                        middle++;
                    }
                    if(grid[row - 1][col - 1] == 1) {
                        middle++;
                    }
                    if(grid[row][col - 1] == 1) {
                        middle++;
                    }
                    if(grid[row - 1][col] == 1) {
                        middle++;
                    }
                    if(grid[row + 1][col + 1] == 1) {
                        middle++;
                    }
                    if(grid[row][col + 1] == 1) {
                        middle++;
                    }
                    if(grid[row - 1][col + 1] == 1) {
                        middle++;
                    }
                    if(grid[row][col] == 1) {
                        if((middle == 2) || (middle == 3 )) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                    if(grid[row][col] == 0) {
                        if(middle == 3 ) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                }
                else if ((row < this.rows - 1) && (row != 0) && (col == 0) ) {
                    System.err.println("The cur row is " + row + " and the last row is " + this.rows);
                    int leftCol = 0;
                    if(grid[row + 1][col] == 1){
                        leftCol++;
                    }
                    if(grid[row + 1][col + 1] == 1) {
                        leftCol++;
                    }
                    if(grid[row - 1][col + 1] == 1) {
                        leftCol++;
                    }
                    if(grid[row][col + 1] == 1) {
                        leftCol++;
                    }
                    if(grid[row - 1][col] == 1) {
                        leftCol++;
                    }
                    if(grid[row][col] == 1) {
                        if((leftCol == 2) || (leftCol == 3 )) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                    if(grid[row][col] == 0) {
                        if(leftCol == 3 ) {
                            tempGrid[row][col] = 1;
                        }
                        else{
                            tempGrid[row][col] = 0;
                        }
                    }
                }
            }
        }
        //3 Copy the values of your temporary grid to the real grid
        this.grid = tempGrid;

        //don't mess with this part
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //don't put code here
    }
}