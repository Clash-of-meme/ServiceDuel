package io.swagger.commons.statistique.impl;/**
 * Created by Guillaume on 10/12/2016.
 */

import io.swagger.api.Constants;
import io.swagger.commons.DuelBO;
import io.swagger.commons.MemeBO;
import io.swagger.commons.UserBO;
import io.swagger.persistence.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {Insert class description here}
 */
public class StatistiqueMemeImpl {

    private static final Logger log = Logger.getLogger(StatistiqueMemeImpl.class);

    /**
     * Creates a new instance of statistiqueMemeImpl
     */

    public StatistiqueMemeImpl() {
    }

    public static Boolean getDateLimiteAtteinte(Date dateduel) {
        LocalDate localDate = LocalDate.now();
        Date date_limite = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).minusDays(Constants.DUREE_DUEL).toInstant());
        if(dateduel.before( date_limite )){
            return false;
        }
            return true;
    }

    public static Date getDateLimite() {

        LocalDate localDate = LocalDate.now();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).minusDays(Constants.DUREE_DUEL).toInstant());

    }

    public static String getLoginUser(int id_user) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from UserBO where id = :id");
            query.setParameter("id",id_user);
            List<UserBO> userBOs = query.getResultList();
            session.close();
            if(userBOs.size()==1) {
                return userBOs.get(0).getLogin();
            }
        }
        catch (ExceptionInInitializerError ex){

        }
        return null;
    }
    public static Integer getIdUser(String token) {

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from UserBO where token = :token");
            query.setParameter("token",token);
            List<UserBO> userBOs = query.getResultList();
            session.close();
            if(userBOs.size()==1) {
                return userBOs.get(0).getId();
            }
        }
        catch (ExceptionInInitializerError ex){

        }
        return Constants.NEGATIVE;
    }

    public static ArrayList<Integer> getMemeIdUser(Integer id){
        ArrayList<Integer> list = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from MemeBO BO where id_user = :id");
            query.setParameter("id",id);
            List<MemeBO> memeBOs = query.getResultList();
            session.close();
            for(MemeBO meme : memeBOs){
                 list.add(meme.getId());
            }
        }
        catch (ExceptionInInitializerError ex){

        }
        return list;
    }

    public static Integer getIdUserByLogin(String login) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from UserBO where login = :login");
            query.setParameter("login",login);
            List<UserBO> userBOs = query.getResultList();
            session.close();
            if(userBOs.size()==1) {
                return userBOs.get(0).getId();
            }
        }
        catch (ExceptionInInitializerError ex){

        }
        return Constants.NEGATIVE;
    }

}
