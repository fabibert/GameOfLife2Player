package State;

public abstract class GolBoard implements Board<GolCell>{
    GolCell[][] board;


    public abstract GolCell[][] getArray();

    public abstract void setArray(GolCell[][] array);
}
