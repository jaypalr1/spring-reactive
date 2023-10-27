package org.squad.persistence.repostiory;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.squad.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends ReactiveCrudRepository<UserEntity, Integer> {

}
