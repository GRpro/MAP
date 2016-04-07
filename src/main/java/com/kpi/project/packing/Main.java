package com.kpi.project.packing;

public class Main {

    public static void main(String[] args) {
        CLB clb = new CLB(1, "CLB0", 15, 40);
        Operation sub = new Operation(1, "SUB", 12, 25);
        Operation add = new Operation(2, "ADD", 3, 5);
        Operation div = new Operation(3, "DIV", 8, 15);
        Operation mul = new Operation(4, "MUL", 7, 12);

        clb.activate(sub, add, div, mul);
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
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
}
