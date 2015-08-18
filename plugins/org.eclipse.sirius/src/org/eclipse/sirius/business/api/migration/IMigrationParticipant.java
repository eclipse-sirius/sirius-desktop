/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.migration;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.sirius.ext.base.Option;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;

/**
 * Interface to contribute to migration process.
 * 
 * @author fbarbin
 * 
 */
public interface IMigrationParticipant {

    /**
     * Identify participants to representations file (aird) migration.
     */
    String REPRESENTATIONSFILE_KIND = "RepresentationsFile"; //$NON-NLS-1$

    /**
     * Identify participants to VSM (odesign) migration.
     */
    String VSM_KIND = "VSM"; //$NON-NLS-1$

    /**
     * Called during the parsing of XMIResources (at loading time). It allows to
     * retrieve a renamed EAttribute from its old name. For example, if an
     * attribute 'aaa' has been renamed to 'bbb', then your MigrationParticipant
     * should return the 'bbb' attribute when given the 'aaa' name.
     * 
     * @param eClass
     *            the given eClass.
     * @param oldName
     *            the attribute name before migration.
     * @param loadedVersion
     *            the loaded version of model to migrate.
     * @return the new structural feature or null if not found. The attribute
     *         corresponding to given old name into given eClass.
     */
    EStructuralFeature getAttribute(EClass eClass, String oldName, String loadedVersion);

    /**
     * Called during the parsing of XMIResources (at loading time). If the
     * feature name has changed, you should return the correct one.
     * 
     * @param eClass
     *            the eClass where looking for feature.
     * @param oldName
     *            the structural feature name before migration.
     * @param loadedVersion
     *            the loaded version of model to migrate.
     * @return the new structural feature corresponding to given old name into
     *         given eClass or null if not found.
     */
    EStructuralFeature getLocalElement(EClass eClass, String oldName, String loadedVersion);

    /**
     * Called during the parsing of XMIResources (at loading time). If an
     * EClassifier name has changed, then you should return the correct one.
     * 
     * @param ePackage
     *            the package where looking for classifier.
     * @param oldName
     *            the old classifier name before migration.
     * @param loadedVersion
     *            the loaded version of model to migrate.
     * @return the new classifier corresponding to the old given name into given
     *         ePackage or null if not found.
     */
    EClassifier getType(EPackage ePackage, String oldName, String loadedVersion);

    /**
     * Called during the parsing of XMIResources (at loading time). If a feature
     * value has changed since a previous version, use this method to return the
     * correct expected value. The feature value do not have to be set here,
     * that will be done by XMLHelper.setValue().
     * 
     * @param object
     *            the object containing the feature.
     * @param feature
     *            the feature to set value.
     * @param value
     *            the initial serialized value.
     * @param loadedVersion
     *            the loaded version of model to migrate.
     * @return a new value if has to be changed otherwise null.
     */
    Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion);

    /**
     * All others post-treatments. Called after the resource loading. All others
     * structural changes in meta-models should be migrated here. This method
     * aims to be implemented to migrate greater meta-model changes or business
     * specific treatments (such as fixing inconsistent data that cannot be
     * simply migrated via a getValue mapping).
     * 
     * @param resource
     *            the loaded resource.
     * @param loadedVersion
     *            the loaded version of model to migrate.
     */
    void postLoad(XMLResource resource, String loadedVersion);

    /**
     * Returns the VP version for which this migration is added. This method is
     * called by the migration framework to determine if at least one migration
     * is needed. This method has to provide the viewpoint version for which
     * your migration is written. The framework doesn't decide whether your
     * participant need to be launched or not, you have to make sure that the
     * viewpoint version number of the loaded resource (available via the
     * version parameter) requires a migration.
     * 
     * @return the VP version for which this migration is added.
     */
    Version getMigrationVersion();

    /**
     * Returns the new fragment if the corresponding reference has changed.
     * 
     * @param uriFragment
     *            the current fragment.
     * @return the optional new uri fragment (none if no changes).
     */
    Option<String> getNewFragment(String uriFragment);

    /**
     * Return the EPackage to use for the given namespace.
     * 
     * @param namespace
     *            the nsURI of the package we are looking for.
     * @param loadedVersion
     *            the version of current loading model
     * @return an EPackage if some mapping exists, null otherwise.
     */
    EPackage getPackage(String namespace, String loadedVersion);

    /**
     * Allows to update the created object just after its creation (the
     * attribute values are not yet loaded from XML file).
     * 
     * @param newObject
     *            the new created object
     * @param loadedVersion
     *            the version of current loading model
     * @return An EObject with updated values or the EObject itself if this
     *         migration has nothing to do.
     */
    EObject updateCreatedObject(EObject newObject, String loadedVersion);

    /**
     * Allows changing the eStructuralFeature when it is about to set the value
     * on the feature. <br>
     * This method should by overloaded in association with
     * <code>IMigrationParticipant.getValue(EObject,EStructuralFeature,Object,String)</code>
     * if the value or its type is different. <br>
     * This method is useful when an EStructuralFeature is not persisted anymore
     * but still remains in the metamodel as volatile. The returned
     * EStructuralFeature is the one which replace eStructuralFeature.
     * 
     * @param eClass
     *            the eClass where looking for feature
     * @param eStructuralFeature
     *            the EStructuralFeature on which looking for the affiliation
     * @param loadedVersion
     *            the version of current loading model
     * @return the replacing EStructuralFeature
     */
    EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature, String loadedVersion);

    /**
     * Called after the processing of an XML end tag. This method is useful for
     * migration logic which needs to access XML Attributes which are not mapped
     * with the Ecore model in any way.
     * 
     * @param doneObject
     *            the current Object in the parsing stack.
     * @param xmlAttributes
     *            the xml attributes of the tag which just got closed.
     * @param uri
     *            The Namespace URI, or the empty string if the element has no
     *            Namespace URI or if Namespace processing is not being
     *            performed.
     * @param localName
     *            the local name (without prefix), or the empty string if
     *            Namespace processing is not being performed.
     * @param qName
     *            the qualified XML name (with prefix), or the empty string if
     *            qualified names are not available.
     * @param loadedVersion
     *            the version of current loading model
     * 
     */
    void postXMLEndElement(Object doneObject, Attributes xmlAttributes, String uri, String localName, String qName, String loadedVersion);
}
