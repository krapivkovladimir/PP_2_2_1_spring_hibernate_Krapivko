package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<User> getUsersByCar(String model, int series) {  // List вместо User
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User u join u.car c " +
                        "where c.model = :model and c.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultList();  // getResultList() вместо uniqueResult()
    }

}
