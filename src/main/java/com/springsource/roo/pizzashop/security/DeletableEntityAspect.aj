package com.springsource.roo.pizzashop.security;

import javax.persistence.Transient;

public aspect DeletableEntityAspect{

	declare parents : com.springsource.roo.pizzashop.domain.* implements DeletableEntity;

	        @Transient
	        private transient boolean DeletableEntity.deletable = true;
	        
	        /** Separate implementation */
	        
	        public boolean DeletableEntity.isDeletable() {
	                return deletable;
	        }
	        
	        public void DeletableEntity.setDeletable(boolean deletable) {
	                this.deletable = deletable;
	        }

}
