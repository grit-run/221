package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {



    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> queryCar = sessionFactory.getCurrentSession().createQuery("from Car");
        return queryCar.getResultList();
    }

    @Override
    public User findOwner(String model, int series) {

        TypedQuery<User> listUser = sessionFactory.getCurrentSession().createQuery("from User JOIN FETCH Car where :model and :series")
                .setParameter("model", model)
                .setParameter("series", series);
        return (User) listUser.getResultList();

    }
}