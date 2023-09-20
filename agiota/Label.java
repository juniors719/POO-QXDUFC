package agiota;

public enum Label {
    GIVE,
    TAKE,
    PLUS;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
