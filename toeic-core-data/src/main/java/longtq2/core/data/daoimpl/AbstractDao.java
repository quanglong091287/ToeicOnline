package longtq2.core.data.daoimpl;

import longtq2.core.common.constant.CoreConstant;
import longtq2.core.common.utils.HibernateUtil;
import longtq2.core.data.dao.GenericDao;
import org.hibernate.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private  Class<T> persitenceClass;
    public AbstractDao(){
        this.persitenceClass = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];

    }
    // ham chuyen tu class sang String de dung cau lenh sql
    public String getPersitenceClassName(){
        return persitenceClass.getSimpleName();
    }

//    protected Session getSession(){
//        return HibernateUtil.getSessionFactory().openSession();
//    }
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        // bat ngoai le duoc nem ra boi transaction
        try {

            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersitenceClassName());// doan nay k append User ma fai dung ham chuyen doi string
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public T finfById(ID id) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            result = (T) session.get(persitenceClass, id);
            if(result == null){
                throw new ObjectNotFoundException("NOT FOUND " + id , null);
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection) {
        List<T> list = new ArrayList<T>();
        Object totalItem = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("from ");
            sql.append(getPersitenceClassName());
            if(property != null && value != null) {
                sql.append(" where ").append(property).append("=: value");
            }
            if(sortExpression != null && sortDirection != null) {
                sql.append(" order by ").append(sortExpression);
                sql.append(" ");
                sql.append(sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc");
            }
            Query query1 =session.createQuery(sql.toString());
            if(property != null && value != null) {
                query1.setParameter("value", value);
            }
            list = query1.list();

            StringBuilder sql1 = new StringBuilder("select count(*) from ");
            sql1.append(getPersitenceClassName());
            if(property != null && value != null) {
                sql1.append(" where ").append(property).append("=: value");
            }
            Query query11 =session.createQuery(sql1.toString());
            if(property != null && value != null) {
                query11.setParameter("value", value);
            }
            totalItem = query11.list().get(0);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return new Object[]{totalItem, list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (ID item: ids){
                T t = (T) session.get(persitenceClass,item);
                session.delete(t);
                count++;
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return count;
    }
}
