/******************************************************************************
 * Copyright (c) 2002, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.providers;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.ui.services.marker.AbstractMarkerNavigationProvider;
import org.eclipse.ui.IEditorPart;

/**
 * Abstract Model Marker Navigation Provider this abstract class provides the necessary wrapping required to perform
 * model operations related to the navigation of markers. If the marker attributes contain model element information
 * that needs to be resolved, the corresponding marker provider should be derived from this class.
 * <p>
 * Derived classes should implement the getContext() and the doGotoMarker() methods. The latter method will be called
 * within a model read operation.
 * <p>
 * Class copied from org.eclipse.gmf.runtime.emf.ui.providers.marker.AbstractModelMarkerNavigationProvider to not run
 * the goto marker in exclusive if refresh at opening is set to true (as the refresh can make modification).
 * 
 * @author Kevin Cornell
 */
public abstract class AbstractModelMarkerNavigationProvider
    extends AbstractMarkerNavigationProvider {

    private TransactionalEditingDomain editingDomain;
    
    /**
     * Perform the feedback for navigating to the given marker within a
     * model read action.
     */
    @Override
    public final void gotoMarker(
        final IEditorPart editor,
        final IMarker marker) {

        // Must save the editor first since it will probably be used in 
        // the logic to obtain the model operation context.
        setEditor(editor);
        
        // Remember the editing domain associated with this editor
        IEditingDomainProvider domainProvider = getEditor().getAdapter(IEditingDomainProvider.class);
        
        if (domainProvider != null) {
            EditingDomain domain = domainProvider.getEditingDomain();
            
            if (domain instanceof TransactionalEditingDomain) {
                editingDomain = (TransactionalEditingDomain) domain;
            }
        }
        
        // We do not run the gotoMarker
        // in runExclusive since tool computation modifies the model.
        AbstractModelMarkerNavigationProvider.super.gotoMarker(editor, marker);
    }
    
    /**
     * Determines the EMF resource to which a marker is attached.
     * 
     * @param marker a marker
     * @return the corresponding EMF resource, or <code>null</code> if either
     *     the marker doesn't {@link IMarker#exists() exist} or its resource
     *     cannot be loaded by EMF
     */
    protected Resource getResource(IMarker marker) {
    	Resource result = null;
    	
    	if (marker.exists()) {
    		// a non-existent marker cannot have a resource
    		
	        IPath resourcePath = marker.getResource().getLocation();
	        
	        if (resourcePath != null) {
	        	// if the resource path is null, then I can't locate any objects
	        	// referenced in this marker on that non-existent resource
	        	 
                ResourceSet resourceSet = editingDomain.getResourceSet();
                URI uri = URI.createFileURI(resourcePath.toOSString());
                result = resourceSet.getResource(uri, true);
	        }
    	}
        
        return result;
    }
    
    /**
     * Given a list of element IDs, looks up the elements, themselves, and
     * returns them in the same order.
     * <p>
     * The <code>ids</code> passed to this method should follow a couple of
     * conventions for compactness of implementation in a marker:
     * <ul>
     *   <li>any ID in the list that is a URI fragment (i.e., does not include
     *       a resource URI) is assumed to be relative to the URI of the
     *       specified <code>resource</code></li>
     *   <li>any ID in the list that does not reference an object in the
     *       specified <code>resource</code> must be a fully-qualified URI
     *       (i.e., it must include a resource URI part)</li>
     * </ul>
     * Thus, in the majority of cases (where all referenced objects are in the
     * resource that has the marker), the object IDs in the marker will be as
     * compact as possible.
     * </p>
     * 
     * 
     * @param ids a list of element IDs, as described above
     * @param resource the resource that has a marker from which the list of
     *     IDs was extracted.  URI fragments in the <code>ids</code> list are
     *     assumed to be relative to this resource's URI.  Must not be
     *     <code>null</code>
     * @return the corresponding list of {@link EObject}s, in the same order as
     *     the <code>ids</code>, though not including any elements that could
     *     not be located (due to stale IDs)
     */
    protected List getEObjects(List ids, Resource resource) {
    	List result = new java.util.ArrayList(ids.size());
    	
    	URI resourceUri = resource.getURI();
    	ResourceSet rset = resource.getResourceSet();
    	
    	if (rset != null) {
    		// can't do anything if the resource is not in a resource set
    		
    		for (Iterator iter = ids.iterator(); iter.hasNext();) {
    			String nextId = (String) iter.next();
    			
    			URI nextUri;
    			
    			int hashPos = nextId.indexOf('#');
    			
    			if (hashPos <= 0) {
    				// the ID is a URI fragment.  Make sure not to omit the
    				//   hash symbol in the fragment if there is one
    				nextUri = resourceUri.appendFragment(
    					nextId.substring(hashPos + 1));
    			} else {
    				// the URI is a fully-qualified one.  The Resource URI
    				// portion will need to be decoded but the EObject fragment
    				// portion will not, because it is decoded by MSL
    				String resUriStr = nextId.substring(0, hashPos);
    				String elemIdStr = nextId.substring(hashPos + 1);
    				
    				try {
    					// use UTF-8 encoding for the URI, which is the
    					//   standard encoding for XMI
    					resUriStr = URLDecoder.decode(resUriStr, "UTF-8"); //$NON-NLS-1$
    				} catch (UnsupportedEncodingException e) {
    					// UTF-8 is always available in any Java platform
    				}
    				
    				nextUri = URI.createURI(resUriStr).appendFragment(elemIdStr);
    			}
    			
    			// load on demand because we need to select the element in an
    			// open model in the UI
    			try {
    				EObject element = rset.getEObject(nextUri, true);
    				
    				if (element != null) {
    					result.add(element);
    				}
    			} catch (WrappedException e) {
    				// this is expected in the case of cross-reference URIs that
    				// can no longer be resolved because of a bad resource
    			}
    		}
    	}
    	
    	return result;
    }

}
