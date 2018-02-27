package Model;

public class Position {

    public int row;
    public int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public boolean equals(Object object)
    {
        boolean scoreKeeper = false;

        if (object != null && object instanceof Position)
        {
            scoreKeeper = (this.row == ((Position)object).row);
            scoreKeeper &= (this.column == ((Position) object).column);
        }

        return scoreKeeper;
    }


}
