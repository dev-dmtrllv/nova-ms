package net.server.task;

import net.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * @author Ronan
 * @info Thread responsible for maintaining coupons EXP & DROP effects active
 */
public class CouponTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(CouponTask.class);

    @Override
    public void run() {
        try {
            try (Connection con = DatabaseConnection.getConnection()) {
                Server.getInstance().updateActiveCoupons(con);
            }
            Server.getInstance().commitActiveCoupons();
        } catch (SQLException sqle) {
            log.error("Error updating coupon effects", sqle);
        }
    }
}
