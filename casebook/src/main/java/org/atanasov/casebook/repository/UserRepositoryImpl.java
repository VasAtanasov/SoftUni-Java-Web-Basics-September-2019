package org.atanasov.casebook.repository;

import org.atanasov.casebook.domain.entities.User;

import javax.ejb.Stateless;

@Stateless
public class UserRepositoryImpl extends BaseCrudRepository<User, String> implements UserRepository {

}
