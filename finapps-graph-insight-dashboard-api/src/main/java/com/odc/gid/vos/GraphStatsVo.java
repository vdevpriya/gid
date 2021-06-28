package com.odc.gid.vos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.RowId;

@Entity(name = "GRAPH_STATS")
@Table(name = "GRAPH_STATS")
public class GraphStatsVo implements Serializable {
	@Id
	@Column(name="ROWID")
	String rowId;
	@Column(name="PROVIDER_ID")
	private Integer providerId;
	@Column(name="METRIC_NAME")
	private String metricName;
	@Column(name="METRIC_GROUP")
	private String metricGroup;
	@Column(name="METRIC_VALUE")
	private Double metricValue;
	@Column(name="GRAPH_DATE")
	private Date graphDate;
	
	
		
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public String getMetricGroup() {
		return metricGroup;
	}
	public void setMetricGroup(String metricGroup) {
		this.metricGroup = metricGroup;
	}
	public Double getMetricValue() {
		return metricValue;
	}
	public void setMetricValue(Double metricValue) {
		this.metricValue = metricValue;
	}
	public Date getGraphDate() {
		return graphDate;
	}
	public void setGraphDate(Date graphDate) {
		this.graphDate = graphDate;
	}
	public String getRowId() {
		return rowId;
	}
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}
	@Override
	public String toString() {
		return "GraphStatsVo [rowId=" + rowId + ", providerId=" + providerId
				+ ", metricName=" + metricName + ", metricGroup=" + metricGroup
				+ ", metricValue=" + metricValue + ", graphDate=" + graphDate
				+ "]";
	}
	
	public GraphStatsVo() {
		
	}
	
	public GraphStatsVo(Integer providerId, String metricName,
			String metricGroup, Double metricValue, Date graphDate) {
		super();
		this.providerId = providerId;
		this.metricName = metricName;
		this.metricGroup = metricGroup;
		this.metricValue = metricValue;
		this.graphDate = graphDate;
	}
}
