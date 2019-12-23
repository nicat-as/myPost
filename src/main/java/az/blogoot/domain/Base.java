package az.blogoot.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Base
 */
public class Base implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3552291033293640978L;
    private long id;
    private LocalDateTime insertDate;
    private LocalDateTime updateDate;

    public Base() {
        this.id = 0L;
        this.insertDate = null;
        this.updateDate = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

}