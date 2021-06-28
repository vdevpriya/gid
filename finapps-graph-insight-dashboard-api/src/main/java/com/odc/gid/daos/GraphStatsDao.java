package com.odc.gid.daos;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.odc.gid.vos.GraphStatsVo;


public interface GraphStatsDao extends  CrudRepository <GraphStatsVo, String>{
	

	public List<GraphStatsVo> findByProviderIdAndGraphDate(Integer providerId, Date graphDate);
	public List<GraphStatsVo> findByProviderIdAndGraphDateAndMetricGroup(Integer providerId, Date graphDate, String metricGroup);
	public GraphStatsVo findTopByProviderIdOrderByGraphDateDesc(Integer providerId); 
	public GraphStatsVo findTopByProviderIdAndMetricGroupOrderByGraphDateDesc(Integer providerId, String metricGroup); 
//	public List<GraphStatsVo> findByProviderIdAndGraphDateAndMetricGroupAndMetricNameIn(Integer providerId, Date graphDate, 
//			String metricGroup, List<String> metricNames);
	@Query("select new com.odc.gid.vos.GraphStatsVo(bp.providerId, "
			+ "bp.metricName, bp.metricGroup, bp.metricValue, bp.graphDate)"
			+ " from GRAPH_STATS bp where bp.providerId = :providerId and bp.metricGroup = :metricGroup "
			+ " and bp.graphDate = :graphDate and bp.metricName in :metricName"
			)
//			+ " bp.revenueDate, bp.buyerPartnerId, bp.buyerPartnerName"
//			+ " order by netpayment desc")
	public List<GraphStatsVo> findByProviderIdAndGraphDateAndMetricGroupAndMetricNameIn(@Param("providerId") Integer providerId,
			@Param("graphDate") Date graphDate, @Param("metricGroup") String metricGroup,  @Param("metricName") Set<String> metricName);
}
