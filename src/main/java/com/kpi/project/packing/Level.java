package com.kpi.project.packing;

import java.util.*;

public class Level {

    private int y;
    private int height;
    private int width;
    private TreeSet<EmptyItem> emptyItems;
    private TreeSet<DecartItem2D> items;

    public Level(int width, int height, int y) {
        this.height = height;
        this.y = y;
        this.width = width;
        emptyItems = new TreeSet<>((o1, o2) -> {
            int delta = o1.getWidth() - o2.getWidth();
            if (delta == 0) {
                delta = o2.getX() - o1.getX();
            }
            return delta;
        });
        EmptyItem ei = new EmptyItem(width, 0);
        emptyItems.add(ei);
        items = new TreeSet<>((o1, o2) -> {
            int delta = o1.getX() - o2.getX();
            return delta;
        });
        items.add(ei);
    }

    public boolean canAccommodate(int width, int height) {
        return this.height >= height && emptyItems.last().getWidth() >= width;
    }

    /**
     * Checks that item can be accmmmodated on the lvl.
     * @param item
     * @return can be accommodated or not.
     */
    public boolean canAccommodate(Item2D item) {
        return canAccommodate(item.getWidth(), item.getHeight());
    }

    /**
     * @return false, if item has not been placed or has already existed
     */
    public boolean accommodate(DecartItem2D item) {
        boolean result = false;
        if (!items.contains(item) && canAccommodate(item)) {
            EmptyItem ei = emptyItems.pollLast();
            item.setX(ei.getX());
            ei.resizeWidth(-item.getWidth());
            ei.addToX(item.getWidth());
            emptyItems.add(ei);
            items.remove(ei);
            items.add(ei);
            items.add(item);
            result = true;
        }
        return result;
    }

    /**
     * @return true, if level contained this item.
     */
    public boolean remove(DecartItem2D item) {
        boolean result = items.remove(item);
        if (result) {
            DecartItem2D itemL = items.floor(item);
            DecartItem2D itemR = items.ceiling(item);
            if (itemL instanceof EmptyItem && itemR instanceof EmptyItem) {
                ((EmptyItem) itemL).resizeWidth(item.getWidth() + itemR.getWidth());
                items.remove(itemR);
                emptyItems.remove(itemR);
                emptyItems.remove(itemL);
                emptyItems.add((EmptyItem)itemL);
            } else if (itemL instanceof EmptyItem) {
                ((EmptyItem) itemL).resizeWidth(item.getWidth());
                emptyItems.remove(itemL);
                emptyItems.add((EmptyItem)itemL);
            } else if (itemR instanceof EmptyItem) {
                ((EmptyItem) itemR).resizeWidth(item.getWidth());
                itemR.setX(item.getX());
                items.remove(itemR);
                items.add(itemR);
                emptyItems.remove(itemR);
                emptyItems.add((EmptyItem)itemR);
            } else {
                EmptyItem ei = new EmptyItem(item.getWidth(), item.getX());
                emptyItems.add(ei);
                items.add(ei);
            }
        }
        return result;
    }

    void setHeight(int height) {
        this.height = height;
    }

    /** @return false, if level contains at least one non empty item. */
    public boolean isFree() {
        return items.size() == 1;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() { return width;}

    public int getY() {return y;}

    public Set<DecartItem2D> getItems() {
        return items;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Level with width = " + getWidth() + " and height = " +
                getHeight() + " at y = " + getY() + ". Contains:\n");
        for (Item2D item : items) {
            result.append("\t"+item.toString() + "\n");
        }
        return result.toString();
    }

    public class EmptyItem extends DecartItem2D {

        public EmptyItem(int width, int x) {
            super(0, "empty", width, -1, x, y);
        }

        /** Width += delta, delta could be < 0. */
        public void resizeWidth(int delta) {
            setWidth(getWidth()+delta);
        }

        public void addToX(int dx) {
            setX(getX() + dx);
        }

        public int getHeight() {
            return height;
        }
    }
}
