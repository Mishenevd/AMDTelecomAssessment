package task3;

/**
 * Pair of elements.
 * For easy stream processing.
 *
 * @author Dmitrii_Mishenev
 */
public class Pair<L,R> {
    private final L left;
    private final R right;

    public Pair(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public R getRight() {
        return right;
    }

    public L getLeft() {
        return left;
    }
}