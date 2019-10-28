package org.atanasov.exodia.repository;

import org.atanasov.exodia.domain.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepositoryImpl extends BaseCrudRepository<User, String> implements UserRepository {

}
