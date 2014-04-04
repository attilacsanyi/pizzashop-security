// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.springsource.roo.pizzashop.service;

import com.springsource.roo.pizzashop.domain.Topping;
import com.springsource.roo.pizzashop.service.ToppingServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect ToppingServiceImpl_Roo_Service {
    
    declare @type: ToppingServiceImpl: @Service;
    
    declare @type: ToppingServiceImpl: @Transactional;
    
    public long ToppingServiceImpl.countAllToppings() {
        return Topping.countToppings();
    }
    
    public void ToppingServiceImpl.deleteTopping(Topping topping) {
        topping.remove();
    }
    
    public Topping ToppingServiceImpl.findTopping(Long id) {
        return Topping.findTopping(id);
    }
    
    public List<Topping> ToppingServiceImpl.findAllToppings() {
        return Topping.findAllToppings();
    }
    
    public Topping ToppingServiceImpl.updateTopping(Topping topping) {
        return topping.merge();
    }
    
}
