package com.odc.gid.controllers;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.odc.gid.utils.AuthorisationUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//import springfox.documentation.annotations.ApiIgnore;
//@CrossOrigin(origins="*.bluekai.com")
@RestController
@Api(value="GetPartnerInfo Controller", description="Manage apis to fetch partner info",tags="GetPartnerInfo RestPoint")
@RequestMapping("/")
public class GetPartnerInfoController {

	private static Logger logger = Logger.getLogger(GetPartnerInfoController.class);
	@RequestMapping(value="/getPartnerInfo", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="Returns Partner Info")
	public ResponseEntity<?> getProvidersRevenue(HttpServletRequest request){//, @ApiIgnore @RequestBody AdminActionsBo adminActionsBo){
		try {
			String bksso = null;
			if(request.getCookies()== null || request.getCookies().length==0) {
				logger.error("--------bksso null or empty ");
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			for(Cookie cookie : request.getCookies()) {
				if(cookie.getName().equalsIgnoreCase("bksso"))
					bksso = cookie.getValue();
			}
			if(bksso== null || bksso.isEmpty()) {
				logger.error("--------bksso null or empty ");
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			logger.info("****Bksso used: "+bksso);
			//requestParams.setBksso(java.net.URLDecoder.decode(requestParams.getBksso(), "UTF-8"));
			String partnerInfo = AuthorisationUtil.getParternInfoForGivenBkSso(bksso);
			//logger.debug(partnerInfo);
			return  new ResponseEntity<String>(partnerInfo, HttpStatus.OK);
		} catch (IOException e) {
			logger.error("IOException: " + e.getMessage());
			return new ResponseEntity<String>("Something went wrong while login", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
