package com.ds.salesforce.dao;

import java.util.List;

import com.sforce.soap.enterprise.sobject.SObject;
import com.util.SFoAuthHandle;

/**
 * 
 * @author ISFBaseDAO is interface Which will Have all Operations Which Must
 *         Done By DAO objects
 *
 */
public interface ISFBaseDAO {

	/**
	 * 
	 * @param sfHandle
	 *            i.e Connection Handle
	 * @return
	 */
	List<Object> listAll(SFoAuthHandle sfHandle);

	/**
	 * 
	 * @param obj
	 *            is Any Object for Insertion
	 * @param sfHandle
	 *            i.e Connection Handle
	 * @return boolean
	 */
	boolean insert(Object obj, SFoAuthHandle sfHandle);

	/**
	 * 
	 * @param obj
	 *            is Any Object For Update
	 * @param sfHandle
	 *            i .e Connection Handle
	 * @return boolean
	 */
	boolean update(Object obj, SFoAuthHandle sfHandle);

	/**
	 * 
	 * @param sobjects
	 *            Pass SObject[] Array To Save Object
	 * @param sfHandle
	 *            i .e Connection Handle
	 * @return boolean
	 */
	boolean commit(SObject[] sobjects, SFoAuthHandle sfHandle);

	/**
	 * 
	 * @param obj
	 *            is Any Object For Deletion
	 * @param sfHandle
	 *            i .e Connection Handle
	 * @return boolean
	 */
	boolean delete(Object obj, SFoAuthHandle sfHandle);

	/**
	 * 
	 * @param orgId
	 *            is Organisation ID For Retrieval of Objects
	 * @param sfHandle
	 *            i .e Connection Handle
	 * @return List of Objects
	 */

	List<Object> findById(String orgId, SFoAuthHandle sfHandle);
}