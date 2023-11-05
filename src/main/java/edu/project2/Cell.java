package edu.project2;

public final class Cell {
    private boolean canGoUp = false;
    private boolean canGoLeft = false;
    private boolean canGoDown = false;
    private boolean canGoRight = false;

    public Cell() {
    }

    public Cell(boolean canGoUp, boolean canGoLeft, boolean canGoDown, boolean canGoRight) {
        this.canGoUp = canGoUp;
        this.canGoLeft = canGoLeft;
        this.canGoDown = canGoDown;
        this.canGoRight = canGoRight;
    }

    public boolean isCanGoUp() {
        return canGoUp;
    }

    public boolean isCanGoLeft() {
        return canGoLeft;
    }

    public boolean isCanGoDown() {
        return canGoDown;
    }

    public boolean isCanGoRight() {
        return canGoRight;
    }

    public void freeUp() {
        canGoUp = true;
    }

    public void freeLeft() {
        canGoLeft = true;
    }

    public void freeDown() {
        canGoDown = true;
    }

    public void freeRight() {
        canGoRight = true;
    }
}
