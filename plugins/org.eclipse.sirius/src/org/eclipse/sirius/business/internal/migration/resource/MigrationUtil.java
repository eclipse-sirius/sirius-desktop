/*******************************************************************************
 * Copyright (c) 2007-2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.migration.resource;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

/**
 * A common class to share migration methods.
 * 
 * @author mchauvin
 */
public final class MigrationUtil {

    /** The modeler description file extension for designer v3. */
    public static final String MODELER_DESCRIPTION_FILE_EXTENSION_V3 = "air"; //$NON-NLS-1$

    private static final String UTF_8 = "UTF-8"; //$NON-NLS-1$

    private static final String DOT = "."; //$NON-NLS-1$

    private MigrationUtil() {
    }

    /**
     * Find an eclass.
     * 
     * @param root
     *            the root package.
     * @param className
     *            the class name
     * @return the eclass if found or <code>null</code> if not
     */
    public static EClass findEClass(final EPackage root, final String className) {
        EClassifier found = root.getEClassifier(className);
        EClass clazz = null;
        if (found instanceof EClass) {
            clazz = (EClass) found;
        } else {
            for (final EPackage subPackage : root.getESubpackages()) {
                found = MigrationUtil.findEClass(subPackage, className);
                if (found != null) {
                    return (EClass) found;
                }
            }
        }
        return clazz;
    }

    /**
     * Get the feature from name.
     * 
     * @param featureMappings
     *            feature name, qualified name mapping.
     * @param eClass
     *            class which hold the feature
     * @param name
     *            feature name
     * @return the feature
     */
    public static EStructuralFeature getEStructuralFeature(final Map<String, String> featureMappings, final EClass eClass, final String name) {
        final String className = eClass.getName();
        final String featureName = className + DOT + name;
        if (featureMappings.containsKey(featureName) || featureMappings.containsKey(name)) {
            String qualifiedAttributeName = featureMappings.get(featureName);
            if (qualifiedAttributeName == null) {
                qualifiedAttributeName = featureMappings.get(name);
            }
            final String[] decomposedName = qualifiedAttributeName.split(Pattern.quote(DOT));
            assert decomposedName.length == 2 : "the mapping is not valid";
            EStructuralFeature result = null;
            final EClass classifier = MigrationUtil.findEClass(eClass.getEPackage(), decomposedName[0]);
            result = classifier.getEStructuralFeature(decomposedName[1]);
            return result;
        }
        return null;
    }

    /**
     * Load a properties file.
     * 
     * @param clazz
     *            the class from which this method is called
     * @param resourceName
     *            the resource name
     * @return a map with all properties
     */
    public static Map<String, String> loadProperties(final Class<?> clazz, final String resourceName) {
        final Properties result = new Properties();
        final InputStream in = new BufferedInputStream(clazz.getResourceAsStream(resourceName));
        try {
            result.load(in);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error("I/O Error", e);
        } finally {
            try {
                in.close();
            } catch (final IOException e) {
                SiriusPlugin.getDefault().error("I/O Error", e);
            }
        }
        final Map<String, String> resultMap = new HashMap<String, String>();
        for (final Entry<Object, Object> entry : result.entrySet()) {
            if (entry.getKey() instanceof String && entry.getValue() instanceof String) {
                resultMap.put((String) entry.getKey(), (String) entry.getValue());
            }
        }
        return resultMap;
    }

    /**
     * Returns the contents of the file.
     * 
     * @param file
     *            the file
     * @param monitor
     *            the progress monitor
     * @return the contents of the file.
     */
    public static String getContents(final File file, final IProgressMonitor monitor) {
        monitor.subTask("Loading File");
        if (file.length() > Integer.MAX_VALUE) {
            // file length is too big to get contents
            SiriusPlugin.getDefault().error("The size of the file is too big to get its contents.", null);
        } else {
            int fileLenght = (int) file.length();
            byte[] b = new byte[fileLenght];
            InputStream in = null;
            try {
                in = new FileInputStream(file);
                int total = 0;
                while (total < file.length()) {
                    int result = in.read(b, total, fileLenght - total);
                    if (result == -1) {
                        break;
                    }
                    total += result;
                }
            } catch (FileNotFoundException e) {
                SiriusPlugin.getDefault().error(e.getMessage(), e);
            } catch (IOException e) {
                SiriusPlugin.getDefault().error(e.getMessage(), e);
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        SiriusPlugin.getDefault().error(e.getMessage(), e);
                    }
                }
            }
            monitor.worked(1);
            try {
                return new String(b, UTF_8);
            } catch (UnsupportedEncodingException e) {
                SiriusPlugin.getDefault().error(e.getMessage(), e);
            }
        }
        monitor.worked(1);
        return ""; //$NON-NLS-1$
    }

    /**
     * Persist contents.
     * 
     * @param file
     *            the file to persist
     * @param contents
     *            the contents to persist
     */
    public static void persitContents(final File file, final String contents) {

        OutputStream outputStream = null;

        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            outputStream.write(contents.getBytes(UTF_8));
        } catch (final FileNotFoundException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } catch (final UnsupportedEncodingException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } catch (final IOException e) {
            SiriusPlugin.getDefault().error(e.getMessage(), e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (final IOException e) {
                    SiriusPlugin.getDefault().error(e.getMessage(), e);
                }
            }
        }
    }

    /**
     * Migration resource handler.
     * 
     * @author mchauvin
     */
    public abstract static class AbstractMigrationResourceHandler extends BasicResourceHandler {

        /**
         * {@inheritDoc}
         * 
         * @see org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler#postLoad(org.eclipse.emf.ecore.xmi.XMLResource,
         *      java.io.InputStream, java.util.Map)
         */
        @Override
        public void postLoad(final XMLResource resource, final InputStream inputStream, final Map<?, ?> options) {
            super.postLoad(resource, inputStream, options);
            handleUnknownData(resource);
        }

        /**
         * handle unknown data in Resource.
         * 
         * @param resource
         *            the resource to check
         */
        protected void handleUnknownData(final XMLResource resource) {
            final Map<EObject, AnyType> extMap = resource.getEObjectToExtensionMap();
            for (final Map.Entry<EObject, AnyType> entry : extMap.entrySet()) {
                handleUnknownData(entry.getKey(), entry.getValue());
            }
        }

        private void handleUnknownData(final EObject eObj, final AnyType unknownData) {
            handleUnknownFeatures(eObj, unknownData.getMixed());
            handleUnknownFeatures(eObj, unknownData.getAnyAttribute());
        }

        private void handleUnknownFeatures(final EObject owner, final FeatureMap featureMap) {
            final Iterator<FeatureMap.Entry> iter = featureMap.iterator();
            while (iter.hasNext()) {
                final FeatureMap.Entry entry = iter.next();
                if (handleFeature(owner, entry)) {
                    iter.remove();
                }
            }
        }

        /**
         * handle feature.
         * 
         * @param owner
         *            the feature owner
         * @param entry
         *            the current FeatureMap.Entry of unknown feature to handle
         * @return true if the unknown feature represented by entry must be
         *         deleted in the resource
         */
        protected abstract boolean handleFeature(final EObject owner, final FeatureMap.Entry entry);
    }

}
