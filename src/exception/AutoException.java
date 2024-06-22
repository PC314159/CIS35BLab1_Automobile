package exception;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoException extends Exception {
    private int errno;
    private String msg;

    public AutoException(int errno, String message) {
        super();
        this.errno = errno;
        this.msg = message;
        log();
    }

    public void log() {
        Logger.getAnonymousLogger().log(Level.WARNING, LocalDateTime.now() + " AutoException - Error " + errno + ": " + msg);
    }

    public void fix(String[] args) {
        if (errno == 1) {
            Fix1to100 f1 = new Fix1to100();
            f1.fix1(args);
        }
        else if (errno == 2) {
            Fix1to100 f2 = new Fix1to100();
            f2.fix2(args);
        }
        else if (errno == 3) {
            Fix1to100 f3 = new Fix1to100();
            f3.fix3(args);
        }
        else if (errno == 4) {
            Fix1to100 f4 = new Fix1to100();
            f4.fix4(args);
        }
        else if (errno == 5) {
            Fix1to100 f5 = new Fix1to100();
            f5.fix5(args);
        }
        else {
            //no solution to fix just yet
        }
    }
}
