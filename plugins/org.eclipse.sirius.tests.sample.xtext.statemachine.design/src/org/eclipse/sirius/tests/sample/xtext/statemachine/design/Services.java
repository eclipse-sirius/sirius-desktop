package org.eclipse.sirius.tests.sample.xtext.statemachine.design;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.tests.sample.xtext.statemachine.NamedElement;

/**
 * The services class used by VSM.
 */
public class Services {
    
    /**
     * Get the first {@link NamedElement} according to the given name.
     * 
     * @param resource
     *            the resource to search in
     * @param name
     *            the non null name
     * @return the first NamedElement found in the resource
     */
    public NamedElement getElementByName(Resource resource, String name) {
        NamedElement namedElement = null;
        TreeIterator<EObject> allContents = resource.getAllContents();
        while (allContents.hasNext()) {
            EObject eObject = allContents.next();
            if (eObject instanceof NamedElement && name.equals(((NamedElement) eObject).getName())) {
                namedElement = (NamedElement) eObject;
                break;
            }
        }
        return namedElement;
    }
}
