package utils.mapper.implement;

import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleNameMapper implements IMapper<String> {
    private final Logger logger = Logger.getLogger(RoleNameMapper.class.getName());

    @Override
    public String mapRow(ResultSet result) {
        try {
            return result.getString("role_name");
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
