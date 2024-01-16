package tipitapi.drawmytodayimprovement.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tipitapi.drawmytodayimprovement.image.entity.ImageEntity;

interface ImageJpaRepository extends JpaRepository<ImageEntity, Long> {
}
