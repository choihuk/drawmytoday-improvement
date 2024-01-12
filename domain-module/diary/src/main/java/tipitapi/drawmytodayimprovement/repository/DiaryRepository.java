package tipitapi.drawmytodayimprovement.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import tipitapi.drawmytodayimprovement.domain.Diary;

@Repository
public interface DiaryRepository {

	Optional<Diary> findById(Long diaryId);
}
