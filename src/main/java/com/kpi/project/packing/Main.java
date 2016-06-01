package com.kpi.project.packing;

public class Main {

    public static void main(String[] args) {
        CLB clb = new CLB(1, "CLB0", 10, 40);
        Operation sub = new Operation(1, "SUB", 7, 25);
        Operation add = new Operation(2, "ADD", 3, 5);
        Operation div = new Operation(3, "DIV", 5, 15);
        Operation modifiedDiv = new Operation(31, "MODDIV", 2, 12);
        Operation mul = new Operation(4, "MUL", 5, 17);
        Operation solveDif = new Operation(5, "DIFFUR", 18, 40);

        clb.activate(sub, add, div, modifiedDiv);
        printMatrix(clb.toMatrix());
        System.out.println(clb.packer.getInfo());
        System.out.println("*******************");
        clb.activate(mul);
        printMatrix(clb.toMatrix());
        System.out.println(clb.packer.getInfo());

    }

    public static void printMatrix(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf("%2d ", m[i][j]);
            }
            System.out.println();
        }
    }
}
