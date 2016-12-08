package cz.fi.muni.pa165.exception;

import org.springframework.dao.DataAccessException;

/**
 *
 * @author Jaromir Sys
 */
public class SoccerRecordsDataAccessException extends DataAccessException {
    
    public SoccerRecordsDataAccessException(String msg) {
        super(msg);
    }
    
    public SoccerRecordsDataAccessException(String msg, Throwable thr) {
        super(msg,thr);
    }
    
    public SoccerRecordsDataAccessException(Throwable thr) {
        super("",thr);
    }
}
