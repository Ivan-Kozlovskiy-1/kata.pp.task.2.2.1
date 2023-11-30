package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import javax.persistence.Query;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCar(String carModel, int carSeries) {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
              "select a from User a join Car b on a.car = b.id where b.model = :model and b.series = :series");
      query.setParameter("model", carModel);
      query.setParameter("series", carSeries);
      return query.getSingleResult();
   }

}
