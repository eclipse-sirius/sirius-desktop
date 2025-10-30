/*******************************************************************************
 * Copyright (c) 2012, 2019 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.migration;

import java.util.List;
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
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;

import com.google.common.collect.Lists;

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
     * Version number return when the representations file has never been loaded since the rewrite of migration
     * framework.
     */
    public static final Version INITIAL_VERSION = Version.parseVersion("0.0.0"); //$NON-NLS-1$

    /**
     * Version 6.5.0 of Sirius.
     */
    public static final Version VERSION_VP_6_5_0 = Version.parseVersion("6.5.0"); //$NON-NLS-1$

    private static final String DISABLE_LOG_MIGRATION_INFO = "org.eclipse.sirius.migration.disableLogMigrationInfo"; //$NON-NLS-1$

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
     * Handle unknown data in Resource according to the current metamodels. This unknown data could need migration.
     * 
     * @param resource
     *            the {@link XMLResource} which can contains unknown data which needs to be migrated
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
        // Don't call handleFeature when iterating the FeatureMap
        // Indeed, the handleFeature allows to set the value of the unknown
        // feature in
        // another feature. Doing this the FeatureMap is updated and may raise
        // concurrency if the Feature is being iterated.
        List<FeatureMap.Entry> entryList = Lists.newArrayList(featureMap.iterator());
        for (FeatureMap.Entry currentEntry : entryList) {
            handleFeature(owner, currentEntry.getEStructuralFeature(), currentEntry.getValue());
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

    /**
     * Log a migration message (unless disabled).
     * 
     * @param message
     *            the migration message.
     */
    protected void logMigrationInfo(String message) {
        if (!Boolean.getBoolean(DISABLE_LOG_MIGRATION_INFO)) {
            SiriusPlugin.getDefault().getLog().info(message);
        }
    }

}
