package tipitapi.drawmytodayimprovement.dto;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public record PageResponse<T>(int page, int size, long total, List<T> content) {

    public <U> PageResponse<U> map(Function<? super T, U> converter) {
        return new PageResponse<>(page, size, total, getConvertedContent(converter));
    }

    public void forEach(Consumer<? super T> action) {
        content.forEach(action);
    }

    private <U> List<U> getConvertedContent(Function<? super T, U> converter) {
        return content.stream().map(converter).toList();
    }

}
