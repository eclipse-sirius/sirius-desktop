/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.design.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.ext.emf.AllContents;

/**
 * Generic Ecore services usable from a VSM.
 */
public class EcoreService {
    /**
     * Returns all the root objects of all the resources in the same
     * resource-set as the specified object.
     * 
     * @param any
     *            an EObject.
     * @return all the root objects in the same resource-set as <code>any</code>
     *         or an empty collection if <code>any</code> is not inside a
     *         resource-set.
     */
    public Collection<EObject> allRoots(EObject any) {
        Resource res = any.eResource();
        if (res != null && res.getResourceSet() != null) {
            Collection<EObject> roots = new ArrayList<EObject>();
            for (Resource childRes : res.getResourceSet().getResources()) {
                roots.addAll(childRes.getContents());
            }
            return roots;
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Gets the containing resource name, or null.
     * 
     * @param current
     *            is the object
     * @return the resource
     */
    public String eResourceName(final EObject current) {
        String eResourceName = null;
        if (current != null) {
            Resource resource = current.eResource();
            if (resource != null) {
                eResourceName = current.eResource().getURI().lastSegment();
            }
        }
        return eResourceName;
    }

    /**
     * Computes the label of an EAttribute.
     */
    public String render(EAttribute attr) {
        return new EAttributeServices().render(attr);
    }

    /**
     * Performs a "direct edit" operation on an EAttribute.
     */
    public EAttribute performEdit(EAttribute attr, String editString) {
        return new EAttributeServices().performEdit(attr, editString);
    }

    /**
     * Computes the label of an EOperation.
     */
    public String render(EOperation op) {
        return new EOperationServices().render(op);
    }

    /**
     * Computes the tooltip of an EOperation.
     * 
     * @param op
     *            the operation to get the tooltip from
     * @return the tooltip of the given EOperation.
     */
    public String renderToolTip(EOperation op) {
        return new EOperationServices().renderTooltip(op);
    }

    /**
     * Performs a "direct edit" operation on an EOperation.
     */
    public EOperation performEdit(EOperation op, String editString) {
        return new EOperationServices().performEdit(op, editString);
    }

    public List<ENamedElement> getAllAssociatedElements(EOperation op) {
        return new EOperationServices().getAllAssociatedElements(op);
    }

    public String renderTooltip(EClass klass) {
        // [eContainer()->filter(ecore::ENamedElement).name + '.' + name/]
        return ((EPackage) klass.eContainer()).getName() + "." + klass.getName();
    }

    /**
     * Computes the label of an EReference.
     */
    public String render(EReference ref) {
        return new EReferenceServices().render(ref);
    }

    /**
     * Performs a "direct edit" operation on an EReference.
     */
    public EReference performEdit(EReference ref, String editString) {
        return new EReferenceServices().performEdit(ref, editString);
    }

    /**
     * Finds a type matching the specified name (case-insensitive) in the same
     * resource-set as obj, or inside Ecore itself if none could be found.
     * 
     * @param obj
     *            the object defining the context in which to look.
     * @param name
     *            the name of the type to look for (case-insensitive). Only
     *            basic type names are supported (no qualified names).
     *            Whitespace before or after the name is ignored.
     * @return the first type found in the resource set or Ecore itself which
     *         matches the specified name.
     */
    public EClassifier findTypeByName(EObject obj, String name) {
        EClassifier result = findTypeByName(allRoots(obj), name);
        if (result == null) {
            result = findTypeByNameFrom(EcorePackage.eINSTANCE, name);
        }
        return result;
    }

    /**
     * Returns the root container; it may be this object itself
     * 
     * @param eObject
     *            the object to get the root container for.
     * @return the root container.
     */
    public EObject getRootContainer(EObject eObject) {
        return EcoreUtil.getRootContainer(eObject);
    }

    private EClassifier findTypeByName(Iterable<EObject> roots, String name) {
        for (EObject root : roots) {
            EClassifier result = findTypeByNameFrom(root, name);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    private EClassifier findTypeByNameFrom(EObject root, String name) {
        if (root instanceof EClassifier && nameMatches((EClassifier) root, name)) {
            return (EClassifier) root;
        }
        for (EObject obj : AllContents.of(root)) {
            if (obj instanceof EClassifier && nameMatches((EClassifier) obj, name)) {
                return (EClassifier) obj;
            }
        }
        return null;
    }

    private boolean nameMatches(EClassifier type, String name) {
        if (type != null && type.getName() != null && name != null) {
            return type.getName().trim().equalsIgnoreCase(name.trim());
        } else {
            return false;
        }
    }
}
