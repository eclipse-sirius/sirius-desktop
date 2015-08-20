/*******************************************************************************
 * Copyright (c) 2006, 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ecore.extender.tool.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.ExtenderPlugin;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;
import org.eclipse.sirius.ecore.extender.tool.internal.ReferencesResolver;
import org.eclipse.sirius.ext.emf.EReferencePredicate;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Utility class for model loading/saving and serialization.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public final class ModelUtils {
    /** Constant for the file encoding system property. */
    private static final String ENCODING_PROPERTY = "file.encoding"; //$NON-NLS-1$

    /**
     * Utility classes don't need to (and shouldn't) be instantiated.
     */
    private ModelUtils() {
        // prevents instantiation
    }

    /**
     * Attaches the given {@link EObject} to a new resource created in a new
     * {@link ResourceSet} with the given URI.
     * 
     * @param resourceURI
     *            URI of the new resource to create.
     * @param root
     *            EObject to attach to a new resource.
     */
    public static void attachResource(final URI resourceURI, final EObject root) {
        final Resource newResource = ModelUtils.createResource(resourceURI);
        newResource.getContents().add(root);
    }

    /**
     * Attaches the given {@link EObject} to a new resource created in the given
     * {@link ResourceSet} with the given URI.
     * 
     * @param resourceURI
     *            URI of the new resource to create.
     * @param resourceSet
     *            ResourceSet in which to create the resource.
     * @param root
     *            EObject to attach to a new resource.
     */
    public static void attachResource(final URI resourceURI, final ResourceSet resourceSet, final EObject root) {
        final Resource newResource = ModelUtils.createResource(resourceURI, resourceSet);
        newResource.getContents().add(root);
    }

    /**
     * This will create a {@link Resource} given the model extension it is
     * intended for.
     * 
     * @param modelURI
     *            {@link org.eclipse.emf.common.util.URI URI} where the model is
     *            stored.
     * @return The {@link Resource} given the model extension it is intended
     *         for.
     */
    public static Resource createResource(final URI modelURI) {
        return ModelUtils.createResource(modelURI, new ResourceSetImpl());
    }

    /**
     * This will create a {@link Resource} given the model extension it is
     * intended for and a ResourceSet.
     * 
     * @param modelURI
     *            {@link org.eclipse.emf.common.util.URI URI} where the model is
     *            stored.
     * @param resourceSet
     *            The {@link ResourceSet} to load the model in.
     * @return The {@link Resource} given the model extension it is intended
     *         for.
     */
    public static Resource createResource(final URI modelURI, final ResourceSet resourceSet) {
        String fileExtension = modelURI.fileExtension();
        if (fileExtension == null || fileExtension.length() == 0) {
            fileExtension = Resource.Factory.Registry.DEFAULT_EXTENSION;
        }

        final Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
        final Object resourceFactory = registry.getExtensionToFactoryMap().get(fileExtension);
        if (resourceFactory != null) {
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(fileExtension, resourceFactory);
        } else {
            resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(fileExtension, new XMIResourceFactoryImpl());
        }

        return resourceSet.createResource(modelURI);
    }

    // TODO unit test this
    /**
     * Loads the models contained by the given directory in the given
     * ResourceSet.
     * 
     * @param directory
     *            The directory from which to load the models.
     * @param resourceSet
     *            The {@link ResourceSet} to load the model in.
     * @return The models contained by the given directory.
     * @throws IOException
     *             Thrown if an I/O operation has failed or been interrupted.
     */
    public static List<EObject> getModelsFrom(final File directory, final ResourceSet resourceSet) throws IOException {
        return ModelUtils.getModelsFrom(directory, null, resourceSet);
    }

    // TODO unit test this
    /**
     * Loads the files with the given extension contained by the given directory
     * as EObjects in the given ResourceSet.
     * <p>
     * The argument <code>extension</code> is in fact the needed suffix for its
     * name in order for a file to be loaded. If it is equal to &quot;rd&quot;,
     * a file named &quot;model.aird&quot; will be loaded, but so would be a
     * file named &quot;Shepherd&quot;.
     * </p>
     * <p>
     * The empty String or <code>null</code> will result in all the files of the
     * given directory to be loaded, and would then be equivalent to
     * {@link #getModelsFrom(File)}.
     * </p>
     * 
     * @param directory
     *            The directory from which to load the models.
     * @param extension
     *            File extension of the files to load. If <code>null</code>,
     *            will consider all extensions.
     * @param resourceSet
     *            The {@link ResourceSet} to load the model in.
     * @return The models contained by the given directory.
     * @throws IOException
     *             Thrown if an I/O operation has failed or been interrupted.
     */
    public static List<EObject> getModelsFrom(final File directory, final String extension, final ResourceSet resourceSet) throws IOException {
        final List<EObject> models = new ArrayList<EObject>();
        final String fileExtension;
        if (extension != null) {
            fileExtension = extension;
        } else {
            fileExtension = ""; //$NON-NLS-1$
        }

        if (directory.exists() && directory.isDirectory() && directory.listFiles() != null) {
            final File[] files = directory.listFiles();

            final StringBuffer pattern = new StringBuffer("[^.].*?\\Q"); //$NON-NLS-1$
            pattern.append(fileExtension);
            pattern.append("\\E"); //$NON-NLS-1$

            for (File file : files) {
                final File aFile = file;
                if (!aFile.isDirectory() && aFile.getName().matches(pattern.toString())) {
                    models.add(ModelUtils.load(aFile, resourceSet));
                }
            }
        }

        return models;
    }

    /**
     * Loads a model from a {@link java.io.File File} in a given
     * {@link ResourceSet}.
     * <p>
     * This will return the first root of the loaded model, other roots can be
     * accessed via the resource's content.
     * </p>
     * 
     * @param file
     *            {@link java.io.File File} containing the model to be loaded.
     * @param resourceSet
     *            The {@link ResourceSet} to load the model in.
     * @return The model loaded from the file.
     * @throws IOException
     *             If the given file does not exist.
     */
    public static EObject load(final File file, final ResourceSet resourceSet) throws IOException {
        return ModelUtils.load(URI.createFileURI(file.getPath()), resourceSet);
    }

    /**
     * Load a model from an {@link java.io.InputStream InputStream} in a given
     * {@link ResourceSet}.
     * <p>
     * This will return the first root of the loaded model, other roots can be
     * accessed via the resource's content.
     * </p>
     * 
     * @param stream
     *            The inputstream to load from
     * @param fileName
     *            The original filename
     * @param resourceSet
     *            The {@link ResourceSet} to load the model in.
     * @return The loaded model
     * @throws IOException
     *             If the given file does not exist.
     */
    public static EObject load(final InputStream stream, final String fileName, final ResourceSet resourceSet) throws IOException {
        if (stream == null) {
            throw new NullPointerException(Messages.ModelUtils_missingInputStream);
        }
        EObject result = null;

        final Resource modelResource = ModelUtils.createResource(URI.createURI(fileName), resourceSet);
        final Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_ENCODING, System.getProperty(ModelUtils.ENCODING_PROPERTY));
        modelResource.load(stream, options);
        if (modelResource.getContents().size() > 0) {
            result = modelResource.getContents().get(0);
        }
        return result;
    }

    /**
     * Loads a model from an {@link org.eclipse.emf.common.util.URI URI} in a
     * given {@link ResourceSet}.
     * <p>
     * This will return the first root of the loaded model, other roots can be
     * accessed via the resource's content.
     * </p>
     * 
     * @param modelURI
     *            {@link org.eclipse.emf.common.util.URI URI} where the model is
     *            stored.
     * @param resourceSet
     *            The {@link ResourceSet} to load the model in.
     * @return The model loaded from the URI.
     * @throws IOException
     *             If the given file does not exist.
     */
    public static EObject load(final URI modelURI, final ResourceSet resourceSet) throws IOException {
        Resource modelResource = resourceSet.getResource(modelURI, false);
        if (modelResource == null) {
            modelResource = ModelUtils.createResource(modelURI, resourceSet);
        }
        Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_ENCODING, System.getProperty(ModelUtils.ENCODING_PROPERTY));
        return ModelUtils.load(modelResource, options);
    }

    /**
     * Loads a resource from an {@link org.eclipse.emf.common.util.URI URI} in a
     * given {@link ResourceSet}.
     * <p>
     * If the load fails, the resource is unloaded and removed from the
     * resourceSet.
     * </p>
     * 
     * @param resourceURI
     *            {@link org.eclipse.emf.common.util.URI URI} where the model is
     *            stored.
     * @param resourceSet
     *            The {@link ResourceSet} to load the model in.
     * @return The resource.
     */
    public static Resource getResource(final ResourceSet resourceSet, final URI resourceURI) {
        Resource resource = null;
        try {
            resource = resourceSet.getResource(resourceURI, true);
        } catch (WrappedException e) {
            if (ExtenderPlugin.getPlugin().isDebugging()) {
                ExtenderPlugin.getPlugin().getLog().log(new Status(IStatus.WARNING, ExtenderPlugin.ID, e.getMessage(), e));
            }
            // Warning: as getResource has been called with loadOnDemand to
            // true, the resource is created with errors but is set on
            // resourceSet
        }

        return resource;
    }

    /**
     * Loads a model from an {@link org.eclipse.emf.common.util.URI URI} in a
     * given {@link ResourceSet} with performance focus.
     * <p>
     * This will return the first root of the loaded model, other roots can be
     * accessed via the resource's content.
     * </p>
     * 
     * @param modelURI
     *            {@link org.eclipse.emf.common.util.URI URI} where the model is
     *            stored.
     * @param resourceSet
     *            The {@link ResourceSet} to load the model in.
     * @return The model loaded from the URI.
     * @throws IOException
     *             If the given file does not exist.
     */
    public static EObject loadFast(final URI modelURI, final ResourceSet resourceSet) throws IOException {
        final Resource modelResource = ModelUtils.createResource(modelURI, resourceSet);
        final Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_ENCODING, System.getProperty(ModelUtils.ENCODING_PROPERTY));
        options.put(XMLResource.OPTION_DEFER_ATTACHMENT, Boolean.TRUE);
        options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, Boolean.TRUE);
        options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, Boolean.FALSE);
        options.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl(true));
        return ModelUtils.load(modelResource, options);
    }

    /**
     * Loads a model from a resource with the given options.
     * <p>
     * This will return the first root of the loaded model, other roots can be
     * accessed via the resource's content.
     * </p>
     * 
     * @param modelResource
     *            the model resource
     * @param options
     *            The EMF options to load the mode.
     * @return The model loaded from the resource.
     * @throws IOException
     *             If the given file does not exist.
     */
    private static EObject load(final Resource modelResource, final Map<Object, Object> options) throws IOException {
        EObject result = null;

        modelResource.load(options);
        if (modelResource.getContents().size() > 0) {
            result = modelResource.getContents().get(0);
        }
        return result;
    }

    /**
     * Saves a model as a file to the given path.
     * 
     * @param root
     *            Root of the objects to be serialized in a file.
     * @param path
     *            File where the objects have to be saved.
     * @throws IOException
     *             Thrown if an I/O operation has failed or been interrupted
     *             during the saving process.
     */
    public static void save(final EObject root, final String path) throws IOException {
        if (root == null) {
            throw new NullPointerException(Messages.ModelUtils_nullSerializationError);
        }

        final Resource newModelResource = ModelUtils.createResource(URI.createFileURI(path));
        newModelResource.getContents().add(root);
        final Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_ENCODING, System.getProperty(ModelUtils.ENCODING_PROPERTY));
        newModelResource.save(options);
    }

    /**
     * Serializes the given EObjet as a String.
     * 
     * @param root
     *            Root of the objects to be serialized.
     * @return The given EObjet serialized as a String.
     * @throws IOException
     *             Thrown if an I/O operation has failed or been interrupted
     *             during the saving process.
     */
    public static String serialize(final EObject root) throws IOException {
        if (root == null) {
            throw new NullPointerException(Messages.ModelUtils_nullSerializationError);
        }

        final XMIResourceImpl newResource = new XMIResourceImpl();
        final StringWriter writer = new StringWriter();
        newResource.getContents().add(root);
        final Map<Object, Object> options = new HashMap<Object, Object>();
        options.put(XMLResource.OPTION_ENCODING, System.getProperty(ModelUtils.ENCODING_PROPERTY));
        newResource.save(writer, options);
        final String result = writer.toString();
        writer.flush();
        return result;
    }

    /**
     * Browse the whole model resolving references.
     * 
     * @param set
     *            any model.
     */
    public static void resolveAll(final ResourceSet set) {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(set);
        final List<Resource> cachedIdsResources = ReferencesResolver.prepareResolveAll(set, authority);
        EcoreUtil.resolveAll(set);
        ReferencesResolver.unprepareResolveAll(authority, cachedIdsResources);
    }

    /**
     * Force the resolution of every proxy in the given resource set selecting
     * the references to resolve with a filter.
     * 
     * @param set
     *            the resource set to resolve.
     * @param filter
     *            predicate responsible to say wether the reference should be
     *            resolved or not.
     */
    public static void resolveAll(final ResourceSet set, EReferencePredicate filter) {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(set);
        final List<Resource> cachedIdsResources = ReferencesResolver.prepareResolveAll(set, authority);
        new ReferencesResolver(set, filter).resolve(new NullProgressMonitor());
        ReferencesResolver.unprepareResolveAll(authority, cachedIdsResources);
    }

    /**
     * Browse the whole model resolving references.
     * 
     * @param root
     *            any model.
     */
    public static void resolveAll(final EObject root) {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(root);
        if (authority != null) {
            authority.setListening(false);
        }
        List<Resource> cachedIDsResources = Collections.emptyList();
        Resource rootResource = root.eResource();
        if (rootResource != null && rootResource.getResourceSet() != null) {
            cachedIDsResources = ModelUtils.cachedEObjectIDs(rootResource.getResourceSet());
        }
        EcoreUtil.resolveAll(root);
        ModelUtils.uncachedEObejctIDs(cachedIDsResources);
        if (authority != null) {
            authority.setListening(true);
        }
    }

    /**
     * Browse the whole model resolving references.
     * 
     * @param res
     *            any model.
     */
    public static void resolveAll(final Resource res) {
        resolveAll(res, false);
    }

    /**
     * Browse the whole model resolving references (and do it recursively in new
     * resources if recursive parameter is true).
     * 
     * @param res
     *            any model.
     * @param recursive
     *            true to resolveAll resources loading during the resolveAll of
     *            the <code>res<code>, false otherwise.
     */
    public static void resolveAll(final Resource res, boolean recursive) {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(res);
        if (authority != null) {
            authority.setListening(false);
        }
        List<Resource> cachedIDsResources = Collections.emptyList();
        if (res != null && res.getResourceSet() != null) {
            cachedIDsResources = ModelUtils.cachedEObjectIDs(res.getResourceSet());
        }
        List<Resource> resourcesBeforeResolveAll = Lists.newArrayList(res.getResourceSet().getResources());
        EcoreUtil.resolveAll(res);
        if (recursive) {
            List<Resource> resourcesAfterResolveAll = Lists.newArrayList(res.getResourceSet().getResources());
            // Remove the known resources
            Iterators.removeAll(resourcesAfterResolveAll.iterator(), resourcesBeforeResolveAll);
            for (Resource resource : resourcesAfterResolveAll) {
                resolveAll(resource, recursive);
            }
        }
        ModelUtils.uncachedEObejctIDs(cachedIDsResources);
        if (authority != null) {
            authority.setListening(true);
        }
    }

    /**
     * Tell whether the eObject references are all unproxified or not.
     * 
     * @param eObject
     *            any instance.
     * @return true if all the references are valid, false otherwise.
     */
    public static boolean validateProxies(final EObject eObject) {
        final Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
        if (diagnostic.getSeverity() == Diagnostic.ERROR) {
            if (ModelUtils.hasAnyFailingProxy(diagnostic)) {
                return false;
            }
        }
        return true;
    }

    private static boolean hasAnyFailingProxy(final Diagnostic diagnostic) {
        boolean result = false;
        if (diagnostic.getCode() == EObjectValidator.EOBJECT__EVERY_PROXY_RESOLVES) {
            result = true;
        }
        for (int i = 0; !result && i < diagnostic.getChildren().size(); i++) {
            result = ModelUtils.hasAnyFailingProxy(diagnostic.getChildren().get(i));
        }
        return result;
    }

    /**
     * Sets a cache for EObject ids for the specified resource set. return all
     * modified resources.
     * 
     * @param resourceSet
     *            the resource set.
     * @return all modified resources.
     */
    public static List<Resource> cachedEObjectIDs(final ResourceSet resourceSet) {
        final List<Resource> result = new LinkedList<Resource>();
        final Iterator<Resource> iterResources = resourceSet.getResources().iterator();
        while (iterResources.hasNext()) {
            final Resource currentResource = iterResources.next();
            if (currentResource instanceof ResourceImpl && ((ResourceImpl) currentResource).getIntrinsicIDToEObjectMap() == null) {
                ((ResourceImpl) currentResource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
                result.add(currentResource);
            }
        }
        return result;
    }

    /**
     * Uncached EObjects IDs of the given resources.
     * 
     * @param resources
     *            the resources.
     */
    public static void uncachedEObejctIDs(final List<Resource> resources) {
        final Iterator<Resource> iterResources = resources.iterator();
        while (iterResources.hasNext()) {
            final Resource currentResource = iterResources.next();
            if (currentResource instanceof ResourceImpl) {
                ((ResourceImpl) currentResource).setIntrinsicIDToEObjectMap(null);
            }
        }
    }
}
