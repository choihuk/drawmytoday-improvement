package tipitapi.drawmytodayimprovement.dto;

public record PageableRequest(int page, int size, Direction direction) {

    public PageableRequest {
        if (page < 0 || size < 1) {
            String errorMessage = String.format("Invalid PageableRequest: page=%d, size=%d,", page, size);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static PageableRequest of(int page, int size, String direction) {
        return new PageableRequest(page, size, Direction.valueOf(direction));
    }

    public boolean isAscending() {
        return direction == Direction.ASC;
    }

    private enum Direction {
        ASC, DESC
    }

}
