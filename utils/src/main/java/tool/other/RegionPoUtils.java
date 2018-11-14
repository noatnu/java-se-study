package tool.other;

import com.google.common.collect.Lists;
import tool.entity.RegionPo;

import java.sql.*;
import java.util.List;

/**
 * @Author noatn
 * @Description
 * @createDate 2018/10/1
 **/
public class RegionPoUtils {
    private static RegionPoUtils regionPoUtils = new RegionPoUtils();
    private volatile ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
    private PreparedStatement pstmt = null;
    private Connection conn = null;

    public RegionPo findById(int id) throws SQLException {
        init();
        pstmt = conn.prepareStatement(RegionPo.findById);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            RegionPo regionPo = new RegionPo();
            regionPo.setId(Integer.parseInt(rs.getString("id")));
            regionPo.setParentId(Integer.parseInt(rs.getString("parent_id")));
            regionPo.setName(rs.getString("name"));
            return regionPo;
        }
        pstmt.close();
        rs.close();
        end();
        return null;
    }

    public RegionPo findRegionPoById(int id) throws SQLException {
        init();
        init();
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(String.format("%s%d", RegionPo.findRegionPoById, id));
        while (rs.next()) {
            RegionPo regionPo = new RegionPo();
            regionPo.setParentId(Integer.parseInt(rs.getString("parent_id")));
            regionPo.setId(Integer.parseInt(rs.getString("id")));
            regionPo.setName(rs.getString("name"));
            return regionPo;
        }
        rs.close();
        stmt.close();
        end();
        return null;
    }

    public List<RegionPo> regionPoList() throws SQLException {
        RegionPo regionPo = null;
        List<RegionPo> regionPos = Lists.newArrayList();
        init();
        pstmt = conn.prepareStatement(RegionPo.selectList);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            regionPo = new RegionPo();
            regionPo.setId(Integer.parseInt(rs.getString("id")));
            regionPo.setParentId(Integer.parseInt(rs.getString("parent_id")));
            regionPo.setName(rs.getString("name"));
            regionPos.add(regionPo);
        }
        pstmt.close();
        rs.close();
        end();
        return regionPos;
    }

    private void init() {
        threadLocal.set(ConnectionUtils.getConnectionUtil().getConnection());
        conn = threadLocal.get();
    }

    private void end() {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static RegionPoUtils getRegionPoUtils() {
        if (regionPoUtils == null) {
            regionPoUtils = new RegionPoUtils();
        }
        return regionPoUtils;
    }

}
