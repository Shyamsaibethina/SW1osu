import components.set.Set;
import components.set.Set1L;

/**
 * Layered implementations of secondary methods {@code add} and {@code remove}
 * for {@code Set}.
 *
 * @param <T>
 *            type of {@code Set} elements
 */
public final class SetSecondary1L<T> extends Set1L<T> {

    /**
     * No-argument constructor.
     */
    public SetSecondary1L() {
        super();
    }

    @Override
    public Set<T> remove(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        boolean found = false;
        for (int i = 0; i < s.size() && !found; i++) {
            T temp = s.removeAny();
            if (!temp.equals(s)) {
                s.add(temp);
            }
        }
        return s;
    }

    @Override
    public void add(Set<T> s) {
        assert s != null : "Violation of: s is not null";
        assert s != this : "Violation of: s is not this";

        for (int i = 0; i < s.size(); i++) {
            T x = s.removeAny();
            if (!this.contains(x)) {
                this.add(x);
            } else {
                s.add(x);
            }
        }
    }

}