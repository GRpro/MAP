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

    public int[][] makeMatrix() {
        Level lastLvl = levels.get(levels.size()-1);
        int result[][] = new int[lastLvl.getY()+lastLvl.getHeight()][lastLvl.getWidth()];
        for (Level lvl : levels) {
            int x = 0;
            for (Item2D item : lvl.items) {
                for (int i = item.getWidth(); i > 0; i--) {
                    for (int y = lvl.getY() + item.getHeight()-1; y >= lvl.getY(); y--) {
                        result[y][x] = item.getId();
                    }
                    x++;
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

    private static class Level {

        private int height;
        private int width;
        private int y;
        private int residualWidth;
        private ArrayList<Item2D> items;

        public Level(int width, int height, int y) {
            this.height = height;
            this.width = width;
            this.y = y;
            this.residualWidth = width;
            items = new ArrayList<Item2D>();
        }

        public boolean canAccommodate(int width, int height) {
            return this.height >= height && this.residualWidth >= width;
        }

        public boolean canAccommodate(Item2D item) {
            return canAccommodate(item.getWidth(), item.getHeight());
        }

        /** @return true, if item has been placed. */
        public boolean accommodate(Item2D item) {
            boolean result = false;
            if (canAccommodate(item)) {
                items.add(item);
                residualWidth -= item.getWidth();
                result = true;
            }
            return result;
        }

        /**
         * After removing item, shifts all items to left.
         * @return true, if level contained this item.
         */
        public boolean removeAndSeal(Item2D item) {
            boolean result = items.remove(item);
            if (result) {
                residualWidth += item.getWidth();
            }
            return result;
        }

        void setHeight(int height) {
            this.height = height;
        }

        /** @return false, if level contains at least one item. */
        public boolean isFree() {
            return getResidualWidth() == getWidth();
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() { return width;}

        public int getResidualWidth() {
            return residualWidth;
        }

        public int getY() {return y;}

        public String toString() {
            StringBuilder result = new StringBuilder("Level with width = " + getWidth() + " and height = " +
                    getHeight() + " at y = " + getY() + ". Contains:\n");
            for (Item2D item : items) {
                result.append("\t"+item.toString() + "\n");
            }
            result.append("Residual width = " + getResidualWidth());
            return result.toString();
        }
    }
}
