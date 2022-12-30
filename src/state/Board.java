package state;

public interface Board<T> {
    T[][] getArray();
    void setArray(T[][] array);
}
