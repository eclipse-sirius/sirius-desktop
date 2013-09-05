/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.util.EContentAdapter;

/**
 * An EMF adapter which delegate at runtime to a specialized one. You should extend this adapter and use the factory to instantiate yout subclass.
 * @author mchauvin
 */
public class SmartAdapter  implements Adapter.Internal {

    private Adapter.Internal delegate;

    /**
     * Instantiate a new smart adapter.
     */
    public SmartAdapter() {
    }
    
    /**
     * Set the delegate.
     * @param delegate the adapter to delegate
     */
    private void setDelegate(Adapter.Internal delegate) {
        this.delegate = delegate;  
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void notifyChanged(Notification notification) {
        delegate.notifyChanged(notification);
    }

    /**
     * {@inheritDoc}
     */
    public Notifier getTarget() {
        return delegate.getTarget();
    }
    
    /**
     * {@inheritDoc}
     */
    public void setTarget(Notifier newTarget) {
        delegate.setTarget(newTarget);
    }

    /**
     * {@inheritDoc}
     */
    public boolean isAdapterForType(Object type) {
        return delegate.isAdapterForType(type);
    }

    /**
     * {@inheritDoc}
     */
    public void unsetTarget(Notifier oldTarget) {
        delegate.unsetTarget(oldTarget);
    }
    
    /**
     * Add the smart adapter to this notifier.
     * @param notifier the notifier
     */
    private void addAdapter(Notifier notifier) {
      notifier.eAdapters().add(this); 
    }
    
    /**
     * Remove the smart adapter to this notifier.
     * @param notifier the notifier
     */
    private  void removeAdapter(Notifier notifier) {
        notifier.eAdapters().remove(this);
    }
    
    /**
     * Factory to use to instantiate you subclass of {@link SmartAdapter}.
     * @author mchv
     *
     */
    public static class Factory {

        /**
         * Create a new instance of smart adapter.
         * @param clazz the class to implement
         * @return the new instance 
         */
        public SmartAdapter newInstance(Class<? extends SmartAdapter> clazz) {
            
            //TODO here we should parse an extension point to find the good Adapter.Internal to implement.
           
            try {
                final SmartAdapter sa =  clazz.newInstance();
                sa.setDelegate(new EContentAdapter() {

                    @Override
                    protected void addAdapter(Notifier notifier) {
                        sa.addAdapter(notifier);
                    }

                    @Override
                    protected void removeAdapter(Notifier notifier) {
                        sa.removeAdapter(notifier);
                    }
                    
                });
                return sa;
            } catch (InstantiationException e) {
                //do nothing
            } catch (IllegalAccessException e) {
                //do nothing
            } catch (SecurityException e) {
                //do nothing
            }  catch (IllegalArgumentException e) {
                //do nothing
            }
            /* should never happen */
            return null;
        }
    }
}
