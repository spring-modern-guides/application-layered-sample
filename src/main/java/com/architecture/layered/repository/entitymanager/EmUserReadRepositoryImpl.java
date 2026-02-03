package com.architecture.layered.repository.entitymanager;

import com.architecture.layered.repository.UserReadRepository;
import com.architecture.layered.repository.userview.UserView;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EmUserReadRepositoryImpl implements UserReadRepository {

    @PersistenceContext
    private EntityManager em;

    public EmUserReadRepositoryImpl(EntityManager em) { this.em = em;}

    @Override
    public Optional<UserView> findById(UUID id) {
        return em.createQuery(
                        """
                        select new com.architecture.layered.repository.userview.UserView(
                             u.id,
                             u.name,
                             u.birthDate
                             )
                             from UserEntity u
                             where u.id = :id
                        """,
                        UserView.class
                )
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    @Override
    public List<UserView> findAll() {
        return em.createQuery(
                        """
                        select new com.architecture.layered.repository.userview.UserView(
                            u.id,
                            u.name,
                            u.birthDate
                            )
                            from UserEntity u
                            order by u.name
                        """,
                        UserView.class
                )
                .getResultList();
    }

    @Override
    public List<UserView> findByNameStartingWith(String prefix) {
        return em.createQuery(
                        """
                        select new com.architecture.user.UserView(
                            u.id,
                            u.name,
                            u.birthDate
                        )
                        from UserEntity u
                        where lower(u.name) like lower(concat(:prefix, '%'))
                        order by u.name
                        """,
                        UserView.class
                )
                .setParameter("prefix", prefix)
                .getResultList();
    }

}

