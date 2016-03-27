package com.kpi.project.packing;

/**
 * Created by aleksandr on 27.03.16.
 */
public class Main {

    public static void main(String[] args) {
        CLB clb = new CLB("00", 15, 40);
        Operation sub = new Operation("SUB", 12, 25);
        Operation add = new Operation("ADD", 3, 5);
        Operation div = new Operation("DIV", 8, 15);
        Operation mul = new Operation("MUL", 7, 12);

        clb.activate(sub, add, div, mul);
        System.out.println(clb.packer.getLevelInfo());
        System.out.println("*******************");
        clb.activate(mul);
        System.out.println(clb.packer.getLevelInfo());
    }
}
