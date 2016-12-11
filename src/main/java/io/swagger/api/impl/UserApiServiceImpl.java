package io.swagger.api.impl;

import io.swagger.api.Constants;
import io.swagger.api.NotFoundException;
import io.swagger.api.UserApiService;
import io.swagger.commons.DuelBO;
import io.swagger.model.Duel;
import io.swagger.persistence.HibernateUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.ArrayList;
import java.util.List;

import static io.swagger.commons.statistique.impl.StatistiqueMemeImpl.*;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-11T15:09:34.277Z")
public class UserApiServiceImpl extends UserApiService {

    private static final Logger logger = LogManager.getLogger(MeApiServiceImpl.class);

    @Override
    public Response userLoginDuelGet(String login, SecurityContext securityContext) throws NotFoundException {
        try {
            Integer id_user = getIdUserByLogin(login);
            logger.info("Id de l'utilisateur : " + id_user);
            if(id_user != -1){
                ArrayList<Integer> listeidmeme = getMemeIdUser(id_user);
                logger.info("Nombre de Meme possédé" + listeidmeme.size());
                if(listeidmeme.size() !=0) {
                    logger.info("Determination des duels ...");
                    Session session = HibernateUtil.getSessionFactory().openSession();
                    session.beginTransaction();
                    Query query = session.createQuery("from DuelBO where id_meme1 IN :id or id_meme2 IN :id");
                    query.setParameterList("id", listeidmeme);
                    List<DuelBO> duelBOs = query.getResultList();
                    logger.info("Nombre de duel trouvé" + duelBOs.size());
                    ArrayList<Duel> duels = new ArrayList<>();
                    logger.info("Calcul en cours ... ");
                    for(DuelBO duelBO : duelBOs){
                        Duel duel = new Duel();
                        duel.setId(duelBO.getId());
                        duel.setStart_date(duelBO.getStart_date());
                        duel.setIdMeme1(duelBO.getId_meme1());
                        duel.setIdMeme2(duelBO.getId_meme2());
                        duel.setVoteMeme1(duelBO.getVote_meme1());
                        duel.setVoteMeme2(duelBO.getVote_meme2());
                        duel.setInProgress(getDateLimiteAtteinte(duelBO.getStart_date()));

                        duels.add(duel);
                    }
                    session.close();
                    return Response.status(Constants.OK).entity(duels).build();
                }
                return Response.status(Constants.NO_CONTENT).build();
            }
            else
            {
                return Response.status(Constants.BAD_REQUEST).build();
            }
        }
        catch (ExceptionInInitializerError ex)
        {
            logger.info("Erreur avec la base de donnée : " + ex);
        }
        return Response.status(Constants.ERROR).build();
    }


}
