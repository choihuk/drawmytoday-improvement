package tipitapi.drawmytodayimprovement.diary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tipitapi.drawmytodayimprovement.diary.entity.DiaryEntity;

interface DiaryJpaRepository extends JpaRepository<DiaryEntity, Long> {

}