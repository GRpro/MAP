package com.kpi.project.packing;

import java.util.ArrayList;


/** Implements FirstFitLevel online packing. */
public class Packer {

    private ArrayList<Level> levels;

    public Packer(int maxWidth, int maxHeight) {
        levels = new ArrayList<Level>();
        levels.add(new Level(maxWidth, maxHeight, 0));
    }

    /**
     * If item can be placed in fully free level,
     * divides it into two levels(first has the same height as item.height()).
     */
    public boolean place(Item2D item) {
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
    public boolean delete(Item2D item) {
        boolean result = false;
        int i = 0;
        for (; i < levels.size() && !result; i++) {
            result = levels.get(i).removeAndSeal(item);
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

    /** Merges two leveles to one. */
    private void merge(int index1, int index2) {
        Level lvl = levels.get(index1);
        lvl.setHeight(lvl.getHeight() + levels.get(index2).getHeight());
        levels.remove(index2);
    }

    public String getLevelInfo() {
        StringBuilder result = new StringBuilder();
        for (Level lvl : levels) {
            result.append(lvl.toString()+"\n");
        }
        return result.toString();
    }
}
