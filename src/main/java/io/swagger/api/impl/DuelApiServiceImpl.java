package io.swagger.api.impl;

import com.sun.javafx.geom.AreaOp;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.Constants;
import io.swagger.api.DuelApiService;
import io.swagger.api.NotFoundException;
import io.swagger.commons.DuelBO;
import io.swagger.commons.MemeBO;
import io.swagger.model.DemandePattern;
import io.swagger.model.Duel;
import io.swagger.persistence.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static io.swagger.commons.statistique.impl.StatistiqueMemeImpl.getDateLimite;
import static io.swagger.commons.statistique.impl.StatistiqueMemeImpl.getDateLimiteAtteinte;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-11T15:09:34.277Z")
public class DuelApiServiceImpl extends DuelApiService {

    private static final Logger logger = LogManager.getLogger(DuelApiServiceImpl.class);

    @Override
    public Response duelDemandePost(DemandePattern demande, SecurityContext securityContext) throws NotFoundException {

        try {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from MemeBO BO where id = :id and in_queue = :inqueue");
            query.setParameter("inqueue", Constants.FALSE);
        query.setParameter("id",demande.getIdMeme());
        logger.info("Verification de l'existance du meme : " +demande.getIdMeme());
        logger.info("Recherche du meme ...");
        List<MemeBO> memeBOs = query.getResultList();
        if(memeBOs.size() == 1){
            logger.info("Meme trouvé");
            logger.info("Recherche d'opposant ...");
            Query query2 = session.createQuery("from MemeBO where in_queue = :inqueue and id != :id");
            query2.setParameter("id",demande.getIdMeme());
            query2.setParameter("inqueue", Constants.TRUE);
            List<MemeBO> opposants = query2.getResultList();
            logger.info(opposants.size());
            if(opposants.size() != 0){
                logger.info("Opposant trouvé");
                DuelBO duel = new DuelBO();
                duel.setId_meme1(demande.getIdMeme());
                duel.setId_meme2(opposants.get(0).getId());
                duel.setVote_meme1(Constants.ZERO);
                duel.setVote_meme2(Constants.ZERO);
                Date date = new Date();
                duel.setStart_date(date);
                session.save(duel);
                MemeBO opposant = opposants.get(0);
                opposant.setIn_queue(Constants.FALSE);
                session.update(opposant);;
                session.getTransaction().commit();
                session.close();


                Duel duel_res = new Duel();
                duel_res.setInProgress(true);
                duel_res.setIdMeme1(duel.getId_meme1());
                duel_res.setIdMeme2(duel.getId_meme2());
                duel_res.setVoteMeme1(duel.getVote_meme1());
                duel_res.setVoteMeme2(duel.getVote_meme2());
                duel_res.setId(duel.getId());
                duel_res.setStart_date(duel.getStart_date());

                return Response.status(Constants.CREATED).entity(duel).build();
            }
            else
            {
                MemeBO challenger = memeBOs.get(0);
                challenger.setIn_queue(Constants.TRUE);
                session.update(challenger);
                session.getTransaction().commit();
                session.close();
                return Response.status(Constants.NO_CONTENT).build();
            }

        }
        else if (memeBOs.size() == 0){
            logger.info("Pas de meme");
            return Response.status(Constants.BAD_REQUEST).build();
        }
        else{
            return Response.status(Constants.ERROR).build();
        }
        } catch (ExceptionInInitializerError ex)
        {
            logger.info("Erreur avec la base de donnée : " + ex);
        }
        return Response.status(Constants.ERROR).build();
    }
    @Override
    public Response duelGet(SecurityContext securityContext) throws NotFoundException {
        ArrayList<Duel> listDuel = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from DuelBO");
            List<DuelBO> duelBOs = query.getResultList();
            session.close();
            for(DuelBO duelBO : duelBOs){
                Duel duel = new Duel();
                duel.setId(duelBO.getId());
                duel.setStart_date(duelBO.getStart_date());
                duel.setIdMeme1(duelBO.getId_meme1());
                duel.setIdMeme2(duelBO.getId_meme2());
                duel.setVoteMeme1(duelBO.getVote_meme1());
                duel.setVoteMeme2(duelBO.getVote_meme2());

                duel.setInProgress(getDateLimiteAtteinte(duelBO.getStart_date()));

                listDuel.add(duel);
            }
            return Response.status(Constants.OK).entity(listDuel).build();
        } catch (ExceptionInInitializerError ex)
        {
            logger.info("Erreur avec la base de donnée : " + ex);
        }
        return Response.status(Constants.ERROR).entity(listDuel).build();
    }

    @Override
    public Response duelIdGet(Integer id, SecurityContext securityContext) throws NotFoundException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from DuelBO where id = :id");
            query.setParameter("id",id);
            List<DuelBO> duelBOs = query.getResultList();
            session.close();
            if(duelBOs.size() == 1 ) {
                Duel duel = new Duel();
                duel.setId(duelBOs.get(0).getId());
                duel.setStart_date(duelBOs.get(0).getStart_date());
                duel.setIdMeme1(duelBOs.get(0).getId_meme1());
                duel.setIdMeme2(duelBOs.get(0).getId_meme2());
                duel.setVoteMeme1(duelBOs.get(0).getVote_meme1());
                duel.setVoteMeme2(duelBOs.get(0).getVote_meme2());

                duel.setInProgress(getDateLimiteAtteinte(duelBOs.get(0).getStart_date()));

                return Response.status(Constants.OK).entity(duel).build();
            }
            else if (duelBOs.size() == 0)
            {
                return Response.status(Constants.BAD_REQUEST).build();
            }
            else{
                return Response.status(Constants.ERROR).build();
            }

        }
        catch (ExceptionInInitializerError ex)
        {
            logger.info("Erreur avec la base de donnée : " + ex);
        }
        return Response.status(Constants.ERROR).build();
    }
    @Override
    public Response duelIdVotePost(Integer id, Integer idMeme, SecurityContext securityContext) throws NotFoundException {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from DuelBO where id = :id and start_date > :datelimite");
            query.setParameter("datelimite", getDateLimite());
            query.setParameter("id",id);
            logger.info("Verification de l'existance du duel : " + id);
            logger.info("Recherche du duel ...");
            List<DuelBO> duelBOs = query.getResultList();
            if(duelBOs.size() == 1){
                logger.info("Duel trouvé");
                DuelBO duel = duelBOs.get(0);
                logger.info("Recherche de l'attribution du vote");
                if (idMeme == duel.getId_meme1() || idMeme == duel.getId_meme2()) {
                    logger.info("¨Participant trouvé");
                    duel.vote(idMeme);
                    session.update(duel);
                    session.close();
                    return Response.status(Constants.CREATED).build();
                }
                logger.info("Participant inconnu");
                session.close();
                return Response.status(Constants.BAD_REQUEST).build();
            }
            else if (duelBOs.size() == 0){
                logger.info("Pas de Duel");
                return Response.status(Constants.BAD_REQUEST).build();
            }
            else{
                return Response.status(Constants.ERROR).build();
            }
        } catch (ExceptionInInitializerError ex)
        {
            logger.info("Erreur avec la base de donnée : " + ex);
        }
        return Response.status(Constants.ERROR).build();
    }
}
