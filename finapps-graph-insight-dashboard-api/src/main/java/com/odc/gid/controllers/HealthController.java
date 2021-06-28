package com.odc.gid.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.odc.gid.utils.GetStackTraceUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//import springfox.documentation.annotations.ApiIgnore;
@CrossOrigin//(origins="*.bluekai.com")
@RestController
@Api(value="Health Controller", description="Manage apis to check health of application",tags="Health RestPoint")
@RequestMapping("/")
public class HealthController {

	private static Logger logger = Logger.getLogger(HealthController.class);
	
	@Autowired
    private DataSource dataSource;
	
	@RequestMapping(value="/appHealth", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="Checks application health")
	public ResponseEntity<?> appHealth(HttpServletRequest request){
		logger.info("Health API is up");
		logger.debug("Health API is up");
		return  new ResponseEntity<String>("App Health OK", HttpStatus.OK);
	}
	@RequestMapping(value="/badrequest", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="Checks application health")
	public ResponseEntity<?> badRequest(HttpServletRequest request){
		logger.debug("400 test api");
		return  new ResponseEntity<String>("This will return 400 status code", HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value="/internalservererror", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="Checks application health")
	public ResponseEntity<?> serverError(HttpServletRequest request){
		logger.debug("400 test api");
		return  new ResponseEntity<String>("This will return 500 status code", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/dbHealth", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="Checks application's DB connection health")
	public ResponseEntity<?> checkDBConnectivity() throws SQLException{
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			logger.debug("Conn is sucessful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			GetStackTraceUtil.getStacktraceString(e);
		}
		if(conn!=null)
		{
			PreparedStatement stmt = conn.prepareStatement(
					"select * from dual",
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery();
			rs.last();
			int rowCnt = rs.getRow();
			rs.beforeFirst();
			return  new ResponseEntity<String>("DB Health OK-Dual count:"+rowCnt, HttpStatus.OK);
		}
		return  new ResponseEntity<String>("Error getting DB connection", HttpStatus.OK);
	}
}
