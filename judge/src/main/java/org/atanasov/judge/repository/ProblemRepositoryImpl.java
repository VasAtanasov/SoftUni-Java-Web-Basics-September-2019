package org.atanasov.judge.repository;

import org.atanasov.judge.domain.entities.Problem;

import javax.ejb.Stateless;

@Stateless
public class ProblemRepositoryImpl extends BaseCrudRepository<Problem, String> implements ProblemRepository {

}
