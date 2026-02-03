package com.architecture.layered.repository.entitymanager;

import com.architecture.layered.model.User;
import com.architecture.layered.repository.UserWriteRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.UUID;

public class EmUserWriteRepositoryImpl implements UserWriteRepository {

    private final EntityManager em;

    public EmUserWriteRepositoryImpl(EntityManager em) { this.em = em;}

    @Override
    @Transactional
    public void save(User user) {
        EntityManagerUserEntity entity = UserMapper.toEntity(user);
        em.merge(entity);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        EntityManagerUserEntity entity = em.find(EntityManagerUserEntity.class, id);
        if (entity != null) { em.remove(entity);}
    }

}
