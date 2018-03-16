package sweeper;

import javax.swing.*;

public class Flag {

    private Matrix flagMap;
    private int totalFlaged;

    int getTotalFlaged() {
        return totalFlaged;
    }

    int getTotalClosed() {
        return totalClosed;
    }

    private int totalClosed;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        totalFlaged = 0;
        totalClosed = Ranges.getSquare();
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        totalClosed--;
    }

    void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
        totalFlaged++;
    }

    void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
        totalFlaged--;
    }

    void setFlagedToLastClosedBoxex() {
        for (Coord coord : Ranges.getAllCoords()) {
            if (Box.CLOSED == flagMap.get(coord)) {
                setFlagedToBox(coord);
            }
        }
    }

    void setBombedToBox(Coord bomb) {
        flagMap.set(bomb, Box.BOMBED);
    }

    void setOpenedToCloseBox(Coord coord) {
        if (Box.CLOSED == flagMap.get(coord)) {
            flagMap.set(coord, Box.OPENED);
        }
    }

    void setNoBombToFlagedBox(Coord coord) {
        if(Box.FLAGED == flagMap.get(coord))
            flagMap.set(coord, Box.NOBOMB);
    }

    public int getCountOfFlagedBoxedAround(Coord coord) {
        int count = 0;
        for(Coord around : Ranges.getCoordsAround(coord)){
           if(flagMap.get(around) == Box.FLAGED){
               count++;
           }
        }
        return count;
    }
}
