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
        boolean sameSame = false;

        if (object != null && object instanceof Position)
        {
            sameSame = this.row == ((Position) object).row;
            sameSame = this.column == ((Position) object).column;
        }

        return sameSame;
    }


}
