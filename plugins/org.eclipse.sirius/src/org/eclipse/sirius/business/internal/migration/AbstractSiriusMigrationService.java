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
package org.eclipse.sirius.business.internal.migration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.sirius.business.api.migration.IMigrationParticipant;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.osgi.framework.Version;
import org.xml.sax.Attributes;

/**
 * Abstract migration service. Provides services to load and delegate to
 * migration participants.
 * 
 * @author fbarbin
 * 
 */
public abstract class AbstractSiriusMigrationService implements IMigrationParticipant {

    /**
     * This option is passed during a resource load if a migration should be
     * done. The value contains a string representation of the loaded version.
     * The value can be null if the resource was created before the current
     * migration mechanism.
     */
    public static final String OPTION_RESOURCE_MIGRATION_LOADEDVERSION = "RESOURCE_MIGRATION_LOADEDVERSION"; //$NON-NLS-1$

    // This migration way was introduced with 6.5.0.201208161001 version
    // for both VSM and representations files.
    private static final Version FIRST_VERSION = new Version("6.5.0.201208161001"); //$NON-NLS-1$

    /**
     * loaded delegates contributions.
     */
    private List<IMigrationParticipant> delegatesParticipants = new ArrayList<IMigrationParticipant>();

    /**
     * The last Sirius version where a migration participant is added (computed
     * from the delegatesParticipants list).
     */
    private Version lastMigrationVersion;

    /**
     * Loads contributions {@link IMigrationParticipant} from extension point
     * <code>org.eclipse.sirius.migration</code>.
     */
    protected void loadContributions() {
        delegatesParticipants.clear();
        IConfigurationElement[] config = EclipseUtil.getConfigurationElementsFor("org.eclipse.sirius.migrationParticipant"); //$NON-NLS-1$
        for (IConfigurationElement configurationElement : config) {
            try {
                String kind = configurationElement.getAttribute("kind"); //$NON-NLS-1$
                if (kind.equals(getKind())) {
                    Object contribution = configurationElement.createExecutableExtension("class"); //$NON-NLS-1$
                    if (contribution instanceof IMigrationParticipant) {
                        delegatesParticipants.add((IMigrationParticipant) contribution);
                    }
                }

            } catch (CoreException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(Status.WARNING, SiriusPlugin.ID, "Cannot instanciate migration contribution", e));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EStructuralFeature getAttribute(EClass eClass, String name, String loadedVersion) {
        EStructuralFeature structuralFeature = null;
        for (IMigrationParticipant contribution : delegatesParticipants) {
            structuralFeature = contribution.getAttribute(eClass, name, loadedVersion);
            if (structuralFeature != null) {
                break;
            }
        }
        return structuralFeature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EStructuralFeature getLocalElement(EClass eClass, String name, String loadedVersion) {
        EStructuralFeature structuralFeature = null;
        for (IMigrationParticipant contribution : delegatesParticipants) {
            structuralFeature = contribution.getLocalElement(eClass, name, loadedVersion);
            if (structuralFeature != null) {
                break;
            }
        }
        return structuralFeature;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EClassifier getType(EPackage ePackage, String name, String loadedVersion) {
        EClassifier classifier = null;
        for (IMigrationParticipant contribution : delegatesParticipants) {
            classifier = contribution.getType(ePackage, name, loadedVersion);
            if (classifier != null) {
                break;
            }
        }
        return classifier;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValue(EObject object, EStructuralFeature feature, Object value, String loadedVersion) {
        Object returnedValue = null;
        for (IMigrationParticipant contribution : delegatesParticipants) {
            returnedValue = contribution.getValue(object, feature, value, loadedVersion);
            if (returnedValue != null) {
                break;
            }
        }
        return returnedValue;
    }

    /**
     * Returns the new fragment if the corresponding reference has changed.
     * 
     * @param uriFragment
     *            the current fragment.
     * @return the optional new uri fragment (none if no changes).
     */
    @Override
    public Option<String> getNewFragment(String uriFragment) {
        Option<String> optionalNewFragment = Options.newNone();
        for (IMigrationParticipant contribution : delegatesParticipants) {
            optionalNewFragment = contribution.getNewFragment(uriFragment);
            if (optionalNewFragment.some()) {
                break;
            }
        }
        return optionalNewFragment;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void postLoad(XMLResource resource, String loadedVersion) {
        for (IMigrationParticipant contribution : delegatesParticipants) {
            contribution.postLoad(resource, loadedVersion);
        }
        removeUnknownData(resource);
    }

    /**
     * Clear the unknown elements of the resource.
     * 
     * NOTE: does not remove proxies.
     * 
     * @param resource
     *            the resource
     */
    private void removeUnknownData(XMLResource resource) {
        Map<EObject, AnyType> eObjectToExtensionMap = resource.getEObjectToExtensionMap();
        Iterator<java.util.Map.Entry<EObject, AnyType>> eObjectToExtensionMapEntriesIterator = eObjectToExtensionMap.entrySet().iterator();
        while (eObjectToExtensionMapEntriesIterator.hasNext()) {
            Map.Entry<EObject, AnyType> entry = eObjectToExtensionMapEntriesIterator.next();
            AnyType unknownData = entry.getValue();
            FeatureMap mixedFeatureMap = unknownData.getMixed();
            Iterator<Entry> mixedFeatureMapEntriesIterator = mixedFeatureMap.iterator();
            while (mixedFeatureMapEntriesIterator.hasNext()) {
                FeatureMap.Entry featureMapEntry = mixedFeatureMapEntriesIterator.next();
                Object value = featureMapEntry.getValue();
                if (value instanceof AnyType) {
                    AnyType anyType = (AnyType) value;
                    if (!anyType.eIsProxy()) {
                        mixedFeatureMapEntriesIterator.remove();
                    }
                }
            }
            FeatureMap anyAttributeFeatureMap = unknownData.getAnyAttribute();
            Iterator<Entry> anyAttributeFeatureMapEntriesIterator = anyAttributeFeatureMap.iterator();
            while (anyAttributeFeatureMapEntriesIterator.hasNext()) {
                FeatureMap.Entry featureMapEntry = anyAttributeFeatureMapEntriesIterator.next();
                Object value = featureMapEntry.getValue();
                if (value instanceof AnyType) {
                    AnyType anyType = (AnyType) value;
                    if (!anyType.eIsProxy()) {
                        anyAttributeFeatureMapEntriesIterator.remove();
                    }
                }
            }
            if (mixedFeatureMap.isEmpty() && anyAttributeFeatureMap.isEmpty()) {
                eObjectToExtensionMapEntriesIterator.remove();
            }
        }
    }

    /**
     * Returns the meta-model kind concerned by that migration service.
     * 
     * @return the string corresponding to {@link IMigrationParticipant}.
     */
    protected abstract String getKind();

    // /**
    // * Provides {@link IMigrationContribution} out of extension point. Those
    // * contributions will be added by loadContributions().
    // *
    // * @return a list of contribution implementing
    // * {@link IMigrationContribution}.
    // */
    // protected abstract List<? extends IMigrationContribution>
    // getAdditionnalContributions();

    /**
     * Provides the last Sirius version where a migration participant is added.
     * 
     * @return the version as a <code>major.minor.micro.qualifier</code> format.
     */
    public Version getLastMigrationVersion() {
        if (lastMigrationVersion == null) {
            // Initialized here to do the initialization only once.
            lastMigrationVersion = FIRST_VERSION;
            for (IMigrationParticipant contribution : delegatesParticipants) {
                if (lastMigrationVersion.compareTo(contribution.getMigrationVersion()) < 0) {
                    lastMigrationVersion = contribution.getMigrationVersion();
                }
            }
        }
        return lastMigrationVersion;
    }

    /**
     * Returns whether the given version need a migration.
     * 
     * @param loadedVersion
     *            the version of current loading model.
     * @return true if loaded version is less than the last Sirius version with
     *         migration, false otherwise.
     */
    public boolean isMigrationNeeded(Version loadedVersion) {
        return getLastMigrationVersion().compareTo(loadedVersion) > 0;
    }

    /**
     * Return the EPackage to use for the given namespace found in the given
     * version, by asking to the migration participants.
     * 
     * @param namespace
     *            the nsURI of the package we are looking for.
     * @param version
     *            the version of current loading model
     * @return an EPackage if some mapping exists, null otherwise.
     */
    @Override
    public EPackage getPackage(String namespace, String version) {
        EPackage returnedValue = null;
        for (IMigrationParticipant contribution : delegatesParticipants) {
            returnedValue = contribution.getPackage(namespace, version);
            if (returnedValue != null) {
                break;
            }
        }
        return returnedValue;
    }

    @Override
    public EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature, String loadedVersion) {
        EStructuralFeature returnedValue = null;
        for (IMigrationParticipant contribution : delegatesParticipants) {
            returnedValue = contribution.getAffiliation(eClass, eStructuralFeature, loadedVersion);
            if (returnedValue != null) {
                break;
            }
        }
        return returnedValue;
    }

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
    @Override
    public EObject updateCreatedObject(EObject newObject, String loadedVersion) {
        EObject returnedValue = newObject;
        for (IMigrationParticipant contribution : delegatesParticipants) {
            returnedValue = contribution.updateCreatedObject(returnedValue, loadedVersion);
        }
        return returnedValue;
    }

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
    @Override
    public void postXMLEndElement(Object doneObject, Attributes xmlAttributes, String uri, String localName, String qName, String loadedVersion) {
        for (IMigrationParticipant contribution : delegatesParticipants) {
            contribution.postXMLEndElement(doneObject, xmlAttributes, uri, localName, qName, loadedVersion);
        }

    }

    @Override
    public Version getMigrationVersion() {
        return null;
    }

}
