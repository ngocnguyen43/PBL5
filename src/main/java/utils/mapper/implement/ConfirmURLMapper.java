package utils.mapper.implement;

import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfirmURLMapper implements IMapper<String> {
    private final Logger logger = Logger.getLogger(ConfirmURLMapper.class.getName());

    @Override
    public String mapRow(ResultSet result) {

        try {
            return result.getString("confirm_url_id");
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
