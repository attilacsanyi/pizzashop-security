// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.springsource.roo.pizzashop.domain;

import com.springsource.roo.pizzashop.domain.Base;
import com.springsource.roo.pizzashop.domain.BaseDataOnDemand;
import com.springsource.roo.pizzashop.service.BaseService;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

privileged aspect BaseDataOnDemand_Roo_DataOnDemand {
    
    declare @type: BaseDataOnDemand: @Component;
    
    private Random BaseDataOnDemand.rnd = new SecureRandom();
    
    private List<Base> BaseDataOnDemand.data;
    
    @Autowired
    BaseService BaseDataOnDemand.baseService;
    
    public Base BaseDataOnDemand.getNewTransientBase(int index) {
        Base obj = new Base();
        setName(obj, index);
        return obj;
    }
    
    public void BaseDataOnDemand.setName(Base obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public Base BaseDataOnDemand.getSpecificBase(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Base obj = data.get(index);
        Long id = obj.getId();
        return baseService.findBase(id);
    }
    
    public Base BaseDataOnDemand.getRandomBase() {
        init();
        Base obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return baseService.findBase(id);
    }
    
    public boolean BaseDataOnDemand.modifyBase(Base obj) {
        return false;
    }
    
    public void BaseDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = baseService.findBaseEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Base' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Base>();
        for (int i = 0; i < 10; i++) {
            Base obj = getNewTransientBase(i);
            try {
                baseService.saveBase(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
