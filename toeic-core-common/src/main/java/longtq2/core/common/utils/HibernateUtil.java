package longtq2.core.common.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//<!-- goi den ham  hibernate.cfg.xml trong toeic-web-->
public class HibernateUtil {
    private  static final SessionFactory sessionFactory = buildSessionFactory();
    // dung static de rut ngan.
    // neu khong dung static thi phai khai bao them
    //HibernateUtil hibernateUtil = new HibernateUtil();
    // va o phan duoi khai bao nhu sau
    //private  final SessionFactory SessionFactory = hibernateUtil.buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
     try {
         //create sesssion factory tu hibernate.cfg.xml
         return new Configuration().configure().buildSessionFactory();
     } catch (Throwable e){
        System.out.println("initial session factory fail");
        throw new ExceptionInInitializerError(e);
     }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
