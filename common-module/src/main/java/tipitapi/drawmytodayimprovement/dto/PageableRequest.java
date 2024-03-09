package tipitapi.drawmytodayimprovement.dto;

public record PageableRequest(int page, int size, String direction) {

    public PageableRequest {
        if (page < 0 || size < 1 || validDirection(direction)) {
            String errorMessage = String.format("Invalid PageableRequest: page=%d, size=%d, direction=%s",
                    page, size, direction);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static PageableRequest of(int page, int size, String direction) {
        return new PageableRequest(page, size, direction);
    }

    private boolean validDirection(String direction) {
        return direction.equals("ASC") || direction.equals("DESC");
    }
}
