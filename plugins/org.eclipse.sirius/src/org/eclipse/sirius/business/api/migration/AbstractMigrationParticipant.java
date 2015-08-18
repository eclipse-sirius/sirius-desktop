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

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;

/**
 * Abstract {@link IMigrationParticipant} implementation providing:
 * <UL>
 * <LI>basic useful methods,</LI>
 * <LI>empty method implementation (that do nothing),</LI>
 * <LI>common constants,</LI>
 * </UL>
 * to concretes implementations.
 * 
 * @author fbarbin
 */
public abstract class AbstractMigrationParticipant implements IMigrationParticipant {
    /**
     * Version number return when the representations file has never been loaded
     * since the rewrite of migration framework.
     */
    public static final Version INITIAL_VERSION = Version.parseVersion("0.0.0"); //$NON-NLS-1$

    /**
     * Version 6.5.0 of Sirius.
     */
    public static final Version VERSION_VP_6_5_0 = Version.parseVersion("6.5.0"); //$NON-NLS-1$

    @Override
    public EStructuralFeature getAttribute(EClass eClass, String name, String loadedVersion) {
        // Nothing to migrate by default.
        return null;
    }

    @Override
    public EStructuralFeature getLocalElement(EClass eClass, String name, String loadedVersion) {
        // Nothing to migrate by default.
        return null;
    }

    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        // Nothing to migrate by default.
        return null;
    }

    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        // Nothing to migrate by default.
        return null;
    }

    @Override
    public Option<String> getNewFragment(String uriFragment) {
        // Nothing to migrate by default.
        return Options.newNone();
    }

    @Override
    public void postLoad(XMLResource resource, String loadedVersion) {
        handleUnknownData(resource);
    }

    @Override
    public EPackage getPackage(String namespace, String loadedVersion) {
        return null;
    }

    @Override
    public EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature, String loadedVersion) {
        return null;
    }

    /**
     * Handle unknown feature of the specified {@link EObject}.
     * 
     * @param owner
     *            the unknown feature owner
     * @param unkownFeature
     *            the unknown feature of the specified {@link EObject}
     * @param valueOfUnknownFeature
     *            the value to migrate of the unknown feature
     */
    protected void handleFeature(final EObject owner, final EStructuralFeature unkownFeature, final Object valueOfUnknownFeature) {

    }

    /**
     * Handle unknown data in Resource according to the current metamodels. This
     * unknown data could need migration.
     * 
     * @param resource
     *            the {@link XMLResource} which can contains unknown data which
     *            needs to be migrated
     */
    private void handleUnknownData(final XMLResource resource) {
        final Map<EObject, AnyType> extMap = resource.getEObjectToExtensionMap();
        for (final Map.Entry<EObject, AnyType> entry : extMap.entrySet()) {
            EObject eObj = entry.getKey();
            AnyType unknownData = entry.getValue();
            handleUnknownFeatures(eObj, unknownData.getMixed());
            handleUnknownFeatures(eObj, unknownData.getAnyAttribute());
        }
    }

    /**
     * Handle the specified {@link EObject} having unknown features.
     * 
     * @param owner
     *            the specified {@link EObject} having unknown features
     * @param featureMap
     *            the unknown features and their values
     */
    private void handleUnknownFeatures(final EObject owner, final FeatureMap featureMap) {
        final Iterator<FeatureMap.Entry> iter = featureMap.iterator();
        while (iter.hasNext()) {
            final FeatureMap.Entry entry = iter.next();
            handleFeature(owner, entry.getEStructuralFeature(), entry.getValue());
        }
    }

    @Override
    public EObject updateCreatedObject(EObject newObject, String loadedVersion) {
        return newObject;
    }

    @Override
    public void postXMLEndElement(Object doneObject, Attributes xmlAttributes, String uri, String localName, String qName, String loadedVersion) {
        // nothing to do by default.
    }

}
