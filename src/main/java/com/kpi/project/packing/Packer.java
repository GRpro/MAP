package com.kpi.project.packing;

import java.util.ArrayList;

/** Implements FirstFitLevel online packing. */
public class Packer {

    private ArrayList<Level> levels;

    public Packer(int maxWidth, int maxHeight) {
        levels = new ArrayList<>();
        levels.add(new Level(maxWidth, maxHeight, 0));
    }

    /**
     * If item can be placed in fully free level,
     * divides it into two levels(first has the same height as item.height()).
     */
    public boolean place(DecartItem2D item) {
        boolean result = false;
        int i = 0;
        for (; i < levels.size() && !result; i++) {
            result = levels.get(i).canAccommodate(item);
        }
        if (result) {
            Level lvl = levels.get(--i);
            if (lvl.isFree() && lvl.getHeight() != item.getHeight()) {
                levels.add(new Level(lvl.getWidth(), lvl.getHeight()-item.getHeight(),
                        item.getHeight()));
                lvl.setHeight(item.getHeight());
            }
            lvl.accommodate(item);
        }
        return result;
    }

    /**
     * If any level is fully free after deleting the last item,
     * tries to merge this level with adjacent free levels.
     */
    public boolean delete(DecartItem2D item) {
        boolean result = false;
        int i = 0;
        for (; i < levels.size() && !result; i++) {
            result = levels.get(i).remove(item);
        }
        if (result) {
            Level lvl = levels.get(--i);
            if (lvl.isFree()) {
                if (i > 0 && levels.get(i - 1).isFree()) {
                    merge(i, i - 1);
                }
                if (i < levels.size() - 1 && levels.get(i + 1).isFree()) {
                    merge(i, i + 1);
                }
            }
        }
        return result;
    }

    public int[][] makeMatrix() {
        Level lastLvl = levels.get(levels.size()-1);
        int result[][] = new int[lastLvl.getY()+lastLvl.getHeight()][lastLvl.getWidth()];
        for (Level lvl : levels) {
            for (DecartItem2D item : lvl.getItems()) {
                int x2 = item.getX()+item.getWidth();
                for (int x = item.getX(); x < x2; x++) {
                    for (int y = lvl.getY() + item.getHeight()-1; y >= lvl.getY(); y--) {
                        result[y][x] = item.getId();
                    }
                }
            }
        }
        return result;
    }


    public String getInfo() {
        StringBuilder result = new StringBuilder();
        for (Level lvl : levels) {
            result.append(lvl.toString()+"\n");
        }
        return result.toString();
    }

    /** Merges two leveles to one. */
    private void merge(int index1, int index2) {
        Level lvl = levels.get(index1);
        lvl.setHeight(lvl.getHeight() + levels.get(index2).getHeight());
        levels.remove(index2);
    }


}
