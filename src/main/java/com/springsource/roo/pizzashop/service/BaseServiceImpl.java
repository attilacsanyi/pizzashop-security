package com.springsource.roo.pizzashop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import com.springsource.roo.pizzashop.domain.Base;
import com.springsource.roo.pizzashop.security.UserContextService;


public class BaseServiceImpl implements BaseService {
	
	private static final String WHO_NOT_SEE_THE_CREATEDBY = "guest";
	
	@Autowired
	private UserContextService userContext;
	
	/**
	 * Before saving base add created by info postfix to each Base name
	 * @param base the base
	 */
    public void saveBase(Base base) {
    	
    	base.setName(base.getName() + addCreatedBy());
    	
        base.persist();
    }

    /**
     * Before retrieving the base list remove the created by info if required
     */
    public List<Base> findBaseEntries(int firstResult, int maxResults) {
    	
    	List<Base> origList = Base.findBaseEntries(firstResult, maxResults);
    	
        return (userContext.getUserName().equals(WHO_NOT_SEE_THE_CREATEDBY)) ? removeCreatedBy(origList) : origList;
    }
    
    /** Users can only update his base */
    @PreAuthorize(	"hasRole('ROLE_USER') and " +
    				"hasPermission(#base, 'update')")
    public Base updateBase(Base base) {
        return base.merge();
    }

	/**
     * Add created by info
     * @return the created by info
     */
	private String addCreatedBy() {
		return " (created by: " + userContext.getUserName() + ")";
	}
	
	/**
	 * Remove the created by info
	 * @param origList the input base list
	 * @return the modified base list
	 */
    private List<Base> removeCreatedBy(List<Base> origList) {
    	
    	List<Base> copyList = new ArrayList<Base>();
    	Base modBase = null;
    	String oldBaseName = null;
    	
		for(Base b : origList)
		{
			oldBaseName = b.getName();
			if (!oldBaseName.contains(WHO_NOT_SEE_THE_CREATEDBY))
			{
				modBase = new Base();
				modBase.setName((oldBaseName.split("\\(")[0]).trim());
				modBase.setId(b.getId());
				modBase.setVersion(b.getVersion());
				copyList.add(modBase);
			}
			else
			{
				copyList.add(b);
			}
			
		}
		return copyList;
	}
	
}
