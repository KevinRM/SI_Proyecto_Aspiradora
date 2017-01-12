package es.ull.etsii.ssii;

public enum VacuumMovement {
	UP, DOWN, LEFT, RIGHT;
	private VacuumMovement next;

    static {
        UP.next = RIGHT;
        RIGHT.next = DOWN;
        DOWN.next = LEFT;
        LEFT.next = UP;
    }

    public VacuumMovement getNextDirection() {
        return next;
    }
}
