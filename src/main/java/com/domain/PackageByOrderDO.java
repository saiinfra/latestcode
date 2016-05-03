package com.domain;

import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author PackageByOrderDO used to Set packageNameList->order
 *
 */
public class PackageByOrderDO {

	Double order;
	List<Object> packageNameList;

	HashMap<Double, List<Object>> map = new HashMap<Double, List<Object>>();

	public PackageByOrderDO() {
		super();
	}

	/**
	 * 
	 * @param order
	 *            is Package order
	 * @param packageNameList
	 *            is List Of Packages Associated With Order
	 */
	public PackageByOrderDO(Double order, List<Object> packageNameList) {
		this.order = order;
		this.packageNameList = packageNameList;
		map.put(order, packageNameList);
	}

	/**
	 * 
	 * @return Order of Package
	 */
	public Double getOrder() {
		return order;
	}

	/**
	 * 
	 * @param Setting
	 *            Order to Package
	 */
	public void setOrder(Double order) {
		this.order = order;
	}

	/**
	 * 
	 * @return Package Name List
	 */
	public List<Object> getPackageNameList() {
		return packageNameList;
	}

	/**
	 * 
	 * @param Setting
	 *            packageNameList
	 */
	public void setPackageNameList(List<Object> packageNameList) {
		this.packageNameList = packageNameList;
	}

	/**
	 * 
	 * @param order
	 * @return getPackageByOrder
	 */
	public List<Object> getPackageByOrder(Double order) {
		return map.get(order);
	}

	/**
	 * 
	 * @return HashMap Object
	 */
	public HashMap<Double, List<Object>> getMap() {
		return map;
	}

	/**
	 * 
	 * @param Setting
	 *            HashMap Object
	 */
	public void setMap(HashMap<Double, List<Object>> map) {
		this.map = map;
	}

	/**
	 * 
	 * @param order
	 *            is Package Order
	 * @param packageNameList
	 *            is No of Packages insertion into Order,packageNameList in
	 *            HashMap
	 */
	public void insert(Double order, List<Object> packageNameList) {
		if (map.containsKey(order)) {
			map.get(order).addAll(packageNameList);
		} else {
			map.put(order, packageNameList);
		}
	}
}
