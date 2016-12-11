package io.swagger.commons;/**
 * Created by Guillaume on 08/12/2016.
 */

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * {Insert class description here}
 */
public class DuelBO implements java.io.Serializable {

    private static final Logger log = Logger.getLogger(DuelBO.class);

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Date start_date;
    private Integer id_meme1;
    private Integer id_meme2;
    private Integer vote_meme1;
    private Integer vote_meme2;

    /**
     * Creates a new instance of MemeBO
     */
    public DuelBO() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Integer getId_meme1() {
        return id_meme1;
    }

    public void setId_meme1(Integer id_meme1) {
        this.id_meme1 = id_meme1;
    }

    public Integer getId_meme2() {
        return id_meme2;
    }

    public void setId_meme2(Integer id_meme2) {
        this.id_meme2 = id_meme2;
    }

    public Integer getVote_meme1() {
        return vote_meme1;
    }

    public void setVote_meme1(Integer vote_meme1) {
        this.vote_meme1 = vote_meme1;
    }

    public Integer getVote_meme2() {
        return vote_meme2;
    }

    public void setVote_meme2(Integer vote_meme2) {
        this.vote_meme2 = vote_meme2;
    }

    public void vote(Integer idMeme) {
        if(idMeme == id_meme1){
            vote_meme1++;
        }
        else if (idMeme == id_meme2){
            vote_meme2++;
        }
    }
}
