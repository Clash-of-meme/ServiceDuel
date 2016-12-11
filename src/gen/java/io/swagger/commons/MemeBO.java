package io.swagger.commons;/**
 * Created by Guillaume on 08/12/2016.
 */

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * {Insert class description here}
 */
public class MemeBO implements java.io.Serializable {

    private static final Logger log = Logger.getLogger(MemeBO.class);

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String url;
    private String name;
    private Integer elo;
    private Date creation_date;
    private Integer in_queue;
    private int id_user;
    /**
     * Creates a new instance of MemeBO
     */
    public MemeBO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getElo() {
        return elo;
    }

    public void setElo(Integer elo) {
        this.elo = elo;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Integer getIn_queue() {
        return in_queue;
    }

    public void setIn_queue(Integer in_queue) {
        this.in_queue = in_queue;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
