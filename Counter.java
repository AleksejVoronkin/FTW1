public class Counter implements AutoCloseable {
    private int count;
    private boolean isClosed;

    public Counter() {
        this.count = 0;
        this.isClosed = false;
    }

    public void add() {
        if (isClosed) {
            throw new IllegalStateException("Невозможно использовать счетчик после его закрытия");
        }
        count++;
    }

    public int getCount() {
        return count;
    }

    @Override
    public void close() {
        if (isClosed) {
            throw new IllegalStateException("Счетчик уже закрыт");
        }
        isClosed = true;
    }
}