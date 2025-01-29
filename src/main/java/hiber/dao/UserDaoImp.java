package hiber.dao;


import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserWithCarByModelAndSeries(String model, int series) {
        TypedQuery<User> query = null;
        try {
            String hql = "FROM User user WHERE user.car.model=:model AND user.car.series=:series";
            query = sessionFactory.getCurrentSession().createQuery(hql);
            query.setParameter("model", model);
            query.setParameter("series", series);
            return query.setMaxResults(1).getSingleResult();
        } catch (NoResultException e) {
            e.getMessage();
            System.out.println("Нет пользователя с такой машиной");
        }
        return new User();
    }
}
