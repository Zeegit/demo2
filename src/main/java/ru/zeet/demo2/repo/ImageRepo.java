package ru.zeet.demo2.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.zeet.demo2.domain.Image;

@Repository
public interface ImageRepo extends CrudRepository<Image, Long> {
}
