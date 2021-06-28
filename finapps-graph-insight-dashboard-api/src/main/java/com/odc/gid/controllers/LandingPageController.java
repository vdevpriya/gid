package com.odc.gid.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.odc.gid.bos.AggregatedResponse;
import com.odc.gid.bos.MetNameValIntPair;
import com.odc.gid.bos.MetNameValPair;
import com.odc.gid.daos.GraphStatsDao;
import com.odc.gid.statics.MetricGroup;
import com.odc.gid.utils.AuthorisationUtil;
import com.odc.gid.utils.GetStackTraceUtil;
import com.odc.gid.utils.ProviderIdMap;
import com.odc.gid.vos.GraphStatsVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin//(origins="*.bluekai.com")
@RestController
@Api(value="LandingPage Controller", description="Manage apis to get dashboard",tags="LandingPage RestPoint")
@RequestMapping("/")
public class LandingPageController {
	private static Logger logger = Logger.getLogger(LandingPageController.class);
	
	
	@Autowired
	private GraphStatsDao graphStatsDao;
	
	@Autowired
	private ProviderIdMap providerIdMap;
	
	@RequestMapping(value="/getDashboard", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="Fetches Dashboard details")
	public ResponseEntity<?> getProvidersRevenue(HttpServletRequest request){//, @ApiIgnore @RequestBody AdminActionsBo adminActionsBo){
		try {
			int providerId = -1;
			logger.debug("From logger:"+request);
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
				providerId = AuthorisationUtil.getParternIdForGivenBkSso(bksso);
				//logger.debug(partnerInfo);
				//return  new ResponseEntity<String>("Hello", HttpStatus.OK);
			} catch (IOException e) {
				logger.error("IOException: " + e.getMessage());
				return new ResponseEntity<String>("Something went wrong while login", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			//Mapping multiple partners to a single partner
			if(providerIdMap.get(providerId)!=null)
				providerId = providerIdMap.get(providerId);
			
			AggregatedResponse aggResponse = new AggregatedResponse();
			GraphStatsVo graphStatsVoMaxDate = graphStatsDao.findTopByProviderIdOrderByGraphDateDesc(providerId);
			if(graphStatsVoMaxDate==null || graphStatsVoMaxDate.getGraphDate()==null)
				return  new ResponseEntity<AggregatedResponse>(aggResponse, HttpStatus.OK);
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd,yyyy");
			String lastUpdatedDate = dateFormat.format(graphStatsVoMaxDate.getGraphDate());
			aggResponse.setLastUpdatedDate(lastUpdatedDate);
			
			//String unit = "M";
			List<GraphStatsVo> graphStatsVoUniqueUsers = graphStatsDao.findByProviderIdAndGraphDateAndMetricGroup(providerId, 
					graphStatsVoMaxDate.getGraphDate(), MetricGroup.UNIQUE_USERS.toString()); /////////
			if(graphStatsVoUniqueUsers!=null && !graphStatsVoUniqueUsers.isEmpty()){
				double uniqueUsers = graphStatsVoUniqueUsers.get(0).getMetricValue();
				//unit = MetricValUnitOperator.getUnit(totalIds);
				aggResponse.setTotalUsers(uniqueUsers); //MetricValUnitOperator.convertIntoUnit(totalIds, unit)
				//NumberFormat.getNumberInstance(Locale.US).format(Double d)
			}

			//aggResponse.setUnit(unit);
			
			List<GraphStatsVo> graphStatsVosUniqDev = graphStatsDao.findByProviderIdAndGraphDateAndMetricGroup(providerId, 
					graphStatsVoMaxDate.getGraphDate(), MetricGroup.IDS_BY_TYPE.toString()); //////////
			aggResponse.setUniqueDevicesData(new ArrayList<MetNameValPair>());
			if(graphStatsVosUniqDev!=null) {
				List<MetNameValPair> uniqueDevicesData = aggResponse.getUniqueDevicesData();
				for(GraphStatsVo graphStatsVo : graphStatsVosUniqDev) {
					uniqueDevicesData.add(new MetNameValPair(graphStatsVo.getMetricName(),
							graphStatsVo.getMetricValue()));
				}
				aggResponse.setUniqueDevicesData(uniqueDevicesData);
			}

			Set<String> nrDevVals = new HashSet<String>();//{"1","2","3","4","5","6","7","8","9","10"};
			nrDevVals.add("1");
			nrDevVals.add("2");
			nrDevVals.add("3");
			nrDevVals.add("4");
			nrDevVals.add("5");
			nrDevVals.add("6");
			nrDevVals.add("7");
			nrDevVals.add("8");
			nrDevVals.add("9");
			nrDevVals.add("10");		
			try {
			List<GraphStatsVo> graphStatsVosDevPerUser = graphStatsDao.findByProviderIdAndGraphDateAndMetricGroup(providerId, 
					graphStatsVoMaxDate.getGraphDate(), MetricGroup.CLUSTER_SIZE_HISTOGRAM.toString()); //////////
			//logger.debug(">>>>>>>>>>>>>>>>>>>>>"+providerId+", "+graphStatsVoMaxDate.getGraphDate()+", "+MetricGroup.CLUSTER_SIZE_HISTOGRAM.toString()+", "+nrDevVals);
			aggResponse.setDevicesPerUserData(new ArrayList<MetNameValIntPair>());
			if(graphStatsVosDevPerUser!=null) {
				//logger.debug("Vo list not null!!!!!!!!!!!!!!"+graphStatsVosDevPerUser.size());
				List<MetNameValIntPair> devPerUserData = aggResponse.getDevicesPerUserData();
				for(GraphStatsVo graphStatsVo : graphStatsVosDevPerUser) {
					//logger.debug(graphStatsVo.toString());
					if(nrDevVals.contains(graphStatsVo.getMetricName()))
						devPerUserData.add(new MetNameValIntPair(Integer.parseInt(graphStatsVo.getMetricName()),
							graphStatsVo.getMetricValue()));
				}
				aggResponse.setDevicesPerUserData(devPerUserData);
			}
			else
				logger.debug("No rows found");
			} catch(Exception e) {
				GetStackTraceUtil.getStacktraceString(e);
			}
			
			
			List<GraphStatsVo> graphStatsVoTotalMatches = graphStatsDao.findByProviderIdAndGraphDateAndMetricGroup(providerId, 
					graphStatsVoMaxDate.getGraphDate(), MetricGroup.TOTAL_MATCHES.toString()); ////////
			if(graphStatsVoTotalMatches!=null && !graphStatsVoTotalMatches.isEmpty()){
				double totalMatches = graphStatsVoTotalMatches.get(0).getMetricValue();
				aggResponse.setTotalMatches(totalMatches);
			}
			
			List<GraphStatsVo> graphStatsVosMatchesByType = graphStatsDao.findByProviderIdAndGraphDateAndMetricGroup(providerId, 
					graphStatsVoMaxDate.getGraphDate(), MetricGroup.MATCHES_BY_TYPE.toString()); ////////
			aggResponse.setPieData(new LinkedHashSet<MetNameValPair>());
			if(graphStatsVosMatchesByType!=null) {
				Set<MetNameValPair> pieData = new TreeSet<MetNameValPair>();//aggResponse.getPieData();
				for(GraphStatsVo graphStatsVo : graphStatsVosMatchesByType) {
					pieData.add(new MetNameValPair(graphStatsVo.getMetricName(),
							graphStatsVo.getMetricValue()));
				}
				int i=1;
				double otherPieVal =0;
				Iterator<MetNameValPair> iterator = pieData.iterator();
				Set<MetNameValPair> outputPieData = aggResponse.getPieData();
				while (iterator.hasNext()) {
					MetNameValPair piePair = iterator.next();
					if(i++>7) {
						//logger.debug("oooooo others-"+piePair.toString());
						otherPieVal += piePair.getmValue();
						iterator.remove();
					}
					else
						outputPieData.add(piePair);
//					logger.debug("iiiiii top10-"+piePair.toString());
				}
				if(otherPieVal > 0) {
					MetNameValPair otherPiePair = new MetNameValPair("other", otherPieVal);
					outputPieData.add(otherPiePair);
				}
				aggResponse.setPieData(outputPieData);
			}
			//aggResponse.setDevicesPerUserData(new ArrayList<MetNameValPair>());
			return  new ResponseEntity<AggregatedResponse>(aggResponse, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			GetStackTraceUtil.getStacktraceString(e);
			return new ResponseEntity<String>("Something went wrong while login", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
