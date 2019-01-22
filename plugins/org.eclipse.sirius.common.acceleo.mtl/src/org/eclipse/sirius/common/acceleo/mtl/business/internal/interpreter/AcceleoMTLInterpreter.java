/*******************************************************************************
 * Copyright (c) 2011, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.CodeSource;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;

import org.eclipse.acceleo.common.IAcceleoConstants;
import org.eclipse.acceleo.common.interpreter.CompilationResult;
import org.eclipse.acceleo.common.interpreter.EvaluationResult;
import org.eclipse.acceleo.common.utils.AcceleoCollections;
import org.eclipse.acceleo.engine.service.EvaluationContext;
import org.eclipse.acceleo.model.mtl.MtlPackage;
import org.eclipse.acceleo.model.mtl.resource.EMtlBinaryResourceFactoryImpl;
import org.eclipse.acceleo.model.mtl.resource.EMtlResourceFactoryImpl;
import org.eclipse.acceleo.parser.interpreter.CompilationContext;
import org.eclipse.acceleo.parser.interpreter.ModuleDescriptor;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.ExpressionsPackage;
import org.eclipse.ocl.util.Bag;
import org.eclipse.sirius.common.acceleo.mtl.AcceleoMTLInterpreterPlugin;
import org.eclipse.sirius.common.acceleo.mtl.Messages;
import org.eclipse.sirius.common.acceleo.mtl.business.api.ResourceFinder;
import org.eclipse.sirius.common.acceleo.mtl.business.api.extension.AbstractImportHandler;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.extension.ImportHandlerRegistry;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.DynamicAcceleoModule.QueryIdentifier;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IConverter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterStatus;
import org.eclipse.sirius.common.tools.api.interpreter.InterpreterStatusFactory;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.interpreter.TypedValidation;
import org.eclipse.sirius.common.tools.api.interpreter.ValidationResult;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.osgi.framework.Bundle;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

/**
 * This interpreter will allow for the use of Acceleo 3 expressions.
 *
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class AcceleoMTLInterpreter implements IInterpreter, TypedValidation {

    /**
     * Encapsulates coercion rules used by this interpreter.
     *
     * @author pcdavid
     *
     */
    private static class MTLConverter implements IConverter {

        @Override
        public OptionalInt toInt(Object rawValue) {
            Integer coerced = AcceleoMTLInterpreter.coerceValue(rawValue, Integer.class);
            if (coerced != null) {
                return OptionalInt.of(coerced.intValue());
            } else {
                return OptionalInt.empty();
            }
        }

        @Override
        public Optional<Boolean> toBoolean(Object rawValue) {
            final Boolean coerced;
            if (rawValue instanceof Boolean) {
                coerced = (Boolean) rawValue;
            } else {
                coerced = AcceleoMTLInterpreter.coerceValue(rawValue, Boolean.class);
            }

            if (coerced != null) {
                return Optional.of(coerced);
            }
            return Optional.empty();
        }

        @Override
        public Optional<String> toString(Object rawValue) {
            if (rawValue != null) {
                return Optional.of(rawValue.toString());
            } else {
                return Optional.empty();
            }
        }

        @Override
        public Optional<EObject> toEObject(Object rawValue) {
            return Optional.ofNullable(AcceleoMTLInterpreter.coerceValue(rawValue, EObject.class));
        }

        @Override
        public Optional<Collection<EObject>> toEObjectCollection(Object rawValue) {
            Collection<EObject> coercedResult = new ArrayList<>();
            if (rawValue instanceof Collection<?>) {
                Iterables.addAll(coercedResult, Iterables.filter((Collection<?>) rawValue, EObject.class));
            } else if (rawValue != null && rawValue.getClass().isArray()) {
                Iterables.addAll(coercedResult, Iterables.filter(Lists.newArrayList((Object[]) rawValue), EObject.class));
            } else {
                EObject coerced = AcceleoMTLInterpreter.coerceValue(rawValue, EObject.class);
                if (coerced != null) {
                    coercedResult.add(coerced);
                }
            }
            return Optional.of(coercedResult);
        }
    }

    /**
     * This represents the prefix of an Acceleo 3 expression.
     *
     * @see IAcceleoConstants#DEFAULT_BEGIN
     */
    private static final String ACCELEO_EXPRESSION_PREFIX = IAcceleoConstants.DEFAULT_BEGIN;

    /**
     * This represents the suffix of an Acceleo 3 expression.
     *
     * @see IAcceleoConstants#INVOCATION_END
     */
    private static final String ACCELEO_EXPRESSION_SUFFIX = IAcceleoConstants.DEFAULT_END_BODY_CHAR + IAcceleoConstants.DEFAULT_END;

    /**
     * This will be used whenever we need to split a path around its separator.
     */
    private static final String FILE_SEPARATOR_REGEX = "/|\\\\"; //$NON-NLS-1$

    /**
     * This will hold all dependencies added to this interpreter through import handlers. Keys are the Java class name
     * in the form <code>my.package.MyClass</code>, values are the Modules returned by the handlers.
     */
    protected final Set<ModuleDescriptor> extendedDependencies = new LinkedHashSet<>();

    /**
     * This will hold all dependencies added to this interpreter. Keys are the dependencies path in the form
     * <code>my::package::myModule</code>, values are the actual URIs of the modules.
     */
    protected final Multimap<String, URI> mtlDependencies = LinkedHashMultimap.create();

    /** Keeps a reference to the workspace files we are using as imports. */
    private final Map<String, URI> javaFiles = new HashMap<>();

    /**
     * This will contain the "dummy" module we use for the compilation. All queries present in an VSM file will have a
     * corresponding [query] block in this dummy.
     */
    private final DynamicAcceleoModule module = new DynamicAcceleoModule();

    /**
     * This map will hold the values associated to given variable names. Note that even if this is a multimap, the
     * variables are considered as a stack in order to be coherent with other interpreters : evaluation will consider
     * the value to be a Collection, but setting/unsetting will only work one object by one object.
     */
    private final ListMultimap<String, Object> variables = AcceleoCollections.newCircularArrayDequeMultimap();

    private final Map<String, String> compilationVariables = new LinkedHashMap<>();

    private final Set<String> variableNsURIs = new LinkedHashSet<>();

    /**
     * This will be updated with the list of accessible viewpoint plugins, if any.
     */
    private final Set<String> viewpointPlugins = new LinkedHashSet<>();

    /**
     * This will be updated with the list of accessible viewpoint projects present in the workspace, if any.
     */
    private final Set<String> viewpointProjects = new LinkedHashSet<>();

    /**
     * This will be used in order to provide our own CrossReferencer to Acceleo.
     */
    private CrossReferencerProviderAdapterFactory adapterFactory;

    /**
     * The converter used to coerce raw results.
     */
    private IConverter converter = new MTLConverter();

    /**
     * Default constructor, will be called from the extension point's registry.
     */
    public AcceleoMTLInterpreter() {
        // Nothing to do
    }
    
    @Override
    public IConverter getConverter() {
        return converter;
    }

    /**
     * Tries and coerce the given <em>object</em> to an instance of the given class.
     *
     * @param <T>
     *            Type to which we need to coerce <em>object</em>.
     * @param object
     *            The object we need to coerce to a given {@link Class}.
     * @param clazz
     *            Class to which we are to cast <em>object</em>.
     * @return <em>object</em> cast to type <em>T</em> if possible, <code>null</code> if not.
     */
    @SuppressWarnings("unchecked")
    private static <T> T coerceValue(Object object, Class<T> clazz) {
        if (object == null) {
            return null;
        }

        T result = null;
        if (clazz.isInstance(object)) {
            result = (T) object;
        } else if (object instanceof IAdaptable) {
            result = ((IAdaptable) object).getAdapter(clazz);
        }

        if (result == null) {
            result = Platform.getAdapterManager().getAdapter(object, clazz);
        }

        return result;
    }

    /**
     * Checks whether the given path exists in the plugins.
     *
     * @param path
     *            The path we need to check.
     * @return <code>true</code> if <em>path</em> denotes an existing plugin resource, <code>false</code> otherwise.
     */
    private static boolean existsInPlugins(String path) {
        try {
            URL url = new URL(path);
            return FileLocator.find(url) != null;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * Checks whether the given path exists in the workspace.
     *
     * @param path
     *            The path we need to check.
     * @return <code>true</code> if <em>path</em> denotes an existing workspace resource, <code>false</code> otherwise.
     */
    private static boolean existsInWorkspace(String path) {
        if (path == null || path.length() == 0) {
            return false;
        }

        return ResourcesPlugin.getWorkspace().getRoot().exists(new Path(path));
    }

    /**
     * Extracts the NsURI of the given EObject's metamodel.
     *
     * @param object
     *            The EObject for which we need the metamodel URI.
     * @return The NsURI of the given EObject's metamodel, <code>null</code> if we could not retrieve it.
     */
    private static String extractNsURI(EObject object) {
        final EPackage pack = object.eClass().getEPackage();
        if (pack != null) {
            final String uri = pack.getNsURI();
            if (uri != null && uri.length() > 0) {
                return uri;
            }
        }
        return null;
    }

    /**
     * Tries and extract the NsURIs of the given object if it is an EObject, or of its children if it is a Collection.
     *
     * @param object
     *            The object from which to try and detect metamodel URIs.
     * @return The extracted URIs.
     */
    private static Set<String> extractNsURIs(Object object) {
        Set<String> uris = new LinkedHashSet<>();

        if (object instanceof EObject) {
            final String uri = AcceleoMTLInterpreter.extractNsURI((EObject) object);
            if (uri != null) {
                uris = Collections.singleton(uri);
            }
        } else if (object instanceof Collection<?>) {
            for (Object child : (Collection<?>) object) {
                final Set<String> childURIs = AcceleoMTLInterpreter.extractNsURIs(child);
                if (!childURIs.isEmpty()) {
                    uris = Sets.union(uris, childURIs);
                }
            }
        }

        return uris;
    }

    /**
     * Returns the last value of the given list.
     * <p>
     * Makes no effort to try and check whether the argument is valid.
     * </p>
     *
     * @param values
     *            List from which we need the last value.
     * @param <V>
     *            Type of the list's values.
     * @return The last value of the given list.
     */
    private static <V> V getLast(List<V> values) {
        final ListIterator<V> iterator = values.listIterator(values.size());
        return iterator.previous();
    }

    /**
     * Returns the qualified name of the given eObject's EClass, OCL style.
     * <p>
     * For example, passing an instance of "eClass" to this would yield <code>ecore::EClass</code>.
     * </p>
     *
     * @param eObject
     *            The EObject for which we need the qualified type name.
     * @return The qualified name of the given eObject's EClass.
     */
    private static String getQualifiedType(EObject eObject) {
        return AcceleoMTLInterpreter.getQualifiedName(eObject.eClass());
    }

    /**
     * Returns the qualified name of the given EClassifier, OCL style.
     *
     * @param classifier
     *            The classifier for which we need the qualified type name.
     * @return The qualified name of the given EClassifier.
     */
    private static String getQualifiedName(EClassifier classifier) {
        final List<String> ancestors = new ArrayList<>();

        EObject current = classifier;
        // this will allow us to break the loop as soon as we encounter a "root"
        // EPackage : an EPackage which NsURI is set
        boolean rootEncountered = false;
        while (current instanceof ENamedElement && !rootEncountered) {
            ancestors.add(((ENamedElement) current).getName());
            if (current instanceof EPackage && ((EPackage) current).getNsURI() != null) {
                rootEncountered = true;
            }
            current = current.eContainer();
        }

        Iterable<String> reversed = Lists.reverse(ancestors);
        return Joiner.on(IAcceleoConstants.NAMESPACE_SEPARATOR).join(reversed);
    }

    /**
     * Tries and infer the OCL type of the given Object.
     *
     * @param obj
     *            Object for which we need an OCL type.
     * @return The inferred OCL type. OCLAny if we could not infer anything more sensible.
     */
    private static String inferOCLType(Object obj) {
        String oclType = "OCLAny"; //$NON-NLS-1$
        final EcoreEnvironment env = (EcoreEnvironment) new EcoreEnvironmentFactory().createEnvironment();
        if (obj instanceof Collection<?>) {
            EClassifier elementType = DynamicAcceleoModule.inferCollectionContentOCLType(env, (Collection<?>) obj);
            oclType = "Sequence("; //$NON-NLS-1$
            if (obj instanceof LinkedHashSet<?>) {
                oclType = "OrderedSet("; //$NON-NLS-1$
            } else if (obj instanceof Set<?>) {
                oclType = "Set("; //$NON-NLS-1$
            } else if (obj instanceof Bag<?>) {
                oclType = "Bag("; //$NON-NLS-1$
            }
            oclType += AcceleoMTLInterpreter.getQualifiedName(elementType) + ')';
        } else {
            oclType = AcceleoMTLInterpreter.getQualifiedName(DynamicAcceleoModule.getOCLType(env, obj));
        }
        return oclType;
    }

    /**
     * Checks whether the given <em>candidatePath</em> ends with the given <em>suffix</em>.
     *
     * @param candidatePath
     *            Path for which we need to check the suffix.
     * @param suffix
     *            Suffix we need <em>candidatePath</em> to have.
     * @return <code>true</code> if <em>candidatePath</em> ends with <em>suffix</em>, <code>false</code> otherwise.
     */
    private static boolean pathEndsWith(String candidatePath, String suffix) {
        final String[] candidateSegments = candidatePath.split(AcceleoMTLInterpreter.FILE_SEPARATOR_REGEX);
        final String[] suffixSegments = suffix.split(AcceleoMTLInterpreter.FILE_SEPARATOR_REGEX);

        int candidateIndex = candidateSegments.length - 1;
        int suffixIndex = suffixSegments.length - 1;
        boolean match = suffixIndex <= candidateIndex;

        while (candidateIndex >= 0 && suffixIndex >= 0 && match) {
            match = suffixSegments[suffixIndex].equals(candidateSegments[candidateIndex]);

            candidateIndex--;
            suffixIndex--;
        }

        return match;
    }

    @Override
    public void activateMetamodels(Collection<MetamodelDescriptor> metamodels) {
        Set<EPackage> additionalEPackages = new LinkedHashSet<>();
        for (MetamodelDescriptor descriptor : metamodels) {
            if (descriptor instanceof EcoreMetamodelDescriptor) {
                EPackage pkg = ((EcoreMetamodelDescriptor) descriptor).resolve();
                if (pkg != null) {
                    additionalEPackages.add(pkg);
                }
            }
        }
        module.registerAdditionalEPackages(additionalEPackages);
    }

    /**
     * {@inheritDoc}
     *
     * @param dependency
     *            Acceleo 3 dependencies should be of the form <em>my::package::myModule</em>. Java dependencies should
     *            be in the form <em>my.package.MyClass</em>.
     * @see org.eclipse.sirius.common.tools.api.interpreter.IInterpreter#addImport(java.lang.String)
     */
    @Override
    public void addImport(String dependency) {
        if (dependency != null && dependency.length() > 0) {
            boolean added = addExtendedImport(dependency);
            if (!added) {
                added = addMTLImport(dependency);
            }
            if (added) {
                invalidateModule();
            }
        }
    }

    @Override
    public void clearImports() {
        mtlDependencies.clear();
        extendedDependencies.clear();
        javaFiles.clear();
        invalidateModule();
    }

    @Override
    public void clearVariables() {
        variables.clear();
        variableNsURIs.clear();
        compilationVariables.clear();
    }

    /**
     * Creates an Acceleo compilation context for the current variables and the given contextual information.
     *
     * @param context
     *            The target EObject of this compilation.
     * @param expression
     *            The expression for which we need a compilation context.
     * @return The newly created context.
     */
    public CompilationContext createCompilationContext(EObject context, String expression) {
        final String targetType = AcceleoMTLInterpreter.getQualifiedType(context);

        String targetNsURI = null;
        final EObject root = EcoreUtil.getRootContainer(context);
        if (root != null) {
            targetNsURI = AcceleoMTLInterpreter.extractNsURI(root);
        }

        /*
         * We will only iterate once on all variables, registering their nsURI and determining their OCL type along the
         * way.
         */
        if (compilationVariables.isEmpty() && !variables.isEmpty()) {
            for (Map.Entry<String, Collection<Object>> entry : variables.asMap().entrySet()) {
                final char[] chars = entry.getKey().toCharArray();
                boolean isDigit = chars.length > 0;
                for (int i = 0; i < chars.length && isDigit; i++) {
                    isDigit = Character.isDigit(chars[i]);
                }
                if (isDigit) {
                    continue;
                }
                List<Object> values = (List<Object>) entry.getValue();
                if (!values.isEmpty()) {
                    final Object actualValue = AcceleoMTLInterpreter.getLast(values);
                    compilationVariables.put(entry.getKey(), AcceleoMTLInterpreter.inferOCLType(actualValue));
                    variableNsURIs.addAll(AcceleoMTLInterpreter.extractNsURIs(actualValue));
                }
            }
        }

        final Set<String> nsURIs;
        if (targetNsURI != null) {
            nsURIs = Sets.union(Collections.singleton(targetNsURI), variableNsURIs);
        } else {
            nsURIs = variableNsURIs;
        }

        return new CompilationContext(expression, targetType, compilationVariables, nsURIs, mtlDependencies, Collections.unmodifiableSet(extendedDependencies));
    }

    /**
     * Creates an Acceleo compilation context for the current variables and the given contextual information.
     *
     * @param context
     *            Context of the current compilation.
     * @param expression
     *            The expression for which we need a compilation context.
     * @param targetType
     *            The target EObject type for this expression.
     * @param variableTypes
     *            Set of all accessible variables.
     * @return The newly created context.
     */
    public CompilationContext createCompilationContext(IInterpreterContext context, String expression, String targetType, Map<String, String> variableTypes) {
        addContextImports(context);

        final Set<String> nsURIs = new LinkedHashSet<>();
        for (EPackage pack : context.getAvailableEPackages()) {
            nsURIs.add(pack.getNsURI());
        }

        return new CompilationContext(expression, targetType, variableTypes, nsURIs, mtlDependencies, Sets.newLinkedHashSet(extendedDependencies));
    }

    @Override
    public void dispose() {
        mtlDependencies.clear();
        extendedDependencies.clear();
        javaFiles.clear();
        module.invalidate();
        variables.clear();
        variableNsURIs.clear();
        compilationVariables.clear();
        viewpointPlugins.clear();
        viewpointProjects.clear();
        if (adapterFactory != null) {
            Platform.getAdapterManager().unregisterAdapters(adapterFactory);
            adapterFactory = null;
        }
    }

    @Override
    public Object evaluate(EObject target, String expression) throws EvaluationException {
        EvaluationResult evaluationResult = internalEvaluate(target, expression);
        // Ignore potential problems for now
        return evaluationResult.getEvaluationResult();
    }

    @Override
    public Collection<String> getImports() {
        Set<String> extendedImports = new LinkedHashSet<>();
        for (ModuleDescriptor moduleDescriptor : extendedDependencies) {
            extendedImports.add(moduleDescriptor.getQualifiedName().replace(IAcceleoConstants.NAMESPACE_SEPARATOR, ".")); //$NON-NLS-1$
        }

        return Sets.union(mtlDependencies.keySet(), extendedImports);
    }

    /**
     * Gives access to the current module maintained by this interpreter. Mainly used to provide completion without
     * re-compiling.
     *
     * @return The current in-memory module maintained by this interpreter.
     */
    public DynamicAcceleoModule getModule() {
        return module;
    }

    @Override
    public String getPrefix() {
        return AcceleoMTLInterpreter.ACCELEO_EXPRESSION_PREFIX;
    }

    @Override
    public Object getVariable(String name) {
        if (variables.containsKey(name)) {
            final List<Object> values = variables.get(name);
            if (!values.isEmpty()) {
                return AcceleoMTLInterpreter.getLast(values);
            }
        }
        return null;
    }

    @Override
    public String getVariablePrefix() {
        return null;
    }

    @Override
    public Map<String, ?> getVariables() {
        return variables.asMap();
    }

    @Override
    public boolean provides(String expression) {
        if (expression != null) {
            return expression.startsWith(AcceleoMTLInterpreter.ACCELEO_EXPRESSION_PREFIX) && expression.endsWith(AcceleoMTLInterpreter.ACCELEO_EXPRESSION_SUFFIX);
        }
        return false;
    }

    @Override
    public void removeImport(String dependency) {
        boolean removed = false;
        if (mtlDependencies.containsKey(dependency)) {
            mtlDependencies.removeAll(dependency);
            removed = true;
        } else if (javaFiles.containsKey(dependency)) {
            javaFiles.remove(dependency);
            removed = true;
        }
        if (removed) {
            invalidateModule();
        }
    }

    @Override
    public void setCrossReferencer(ECrossReferenceAdapter crossReferencer) {
        if (adapterFactory == null) {
            adapterFactory = new CrossReferencerProviderAdapterFactory(crossReferencer);
            Platform.getAdapterManager().registerAdapters(adapterFactory, EObject.class);
        }
    }

    @Override
    public void setModelAccessor(ModelAccessor modelAccessor) {
        // Nothing to do
    }

    @Override
    public void setProperty(Object key, Object value) {
        /*
         * This is called by the framework with the FILES key in order to pass us all the VSM files as a Collection.
         */
        if (IInterpreter.FILES.equals(key)) {
            if (value == null) {
                viewpointProjects.clear();
                viewpointPlugins.clear();
            } else if (value instanceof Collection<?>) {
                for (final String odesignPath : Iterables.filter((Collection<?>) value, String.class)) {
                    final URI workspaceCandidate = URI.createPlatformResourceURI(odesignPath, true);
                    final URI pluginCandidate = URI.createPlatformPluginURI(odesignPath, true);
                    if (AcceleoMTLInterpreter.existsInWorkspace(workspaceCandidate.toPlatformString(true))) {
                        viewpointProjects.add(workspaceCandidate.segment(1));
                    } else if (AcceleoMTLInterpreter.existsInPlugins(URI.decode(pluginCandidate.toString()))) {
                        viewpointPlugins.add(pluginCandidate.segment(1));
                    }
                }
            }
        }
    }

    @Override
    public void setVariable(String name, Object value) {
        variables.put(name, value);
        variableNsURIs.clear();
        compilationVariables.clear();
    }

    @Override
    public boolean supportsValidation() {
        return true;
    }

    @Override
    public void unSetVariable(String name) {
        if (variables.containsKey(name)) {
            final List<Object> values = variables.get(name);
            if (!values.isEmpty()) {
                final ListIterator<?> iterator = values.listIterator(values.size());
                iterator.previous();
                iterator.remove();
            }
            variableNsURIs.clear();
            compilationVariables.clear();
        }
    }

    @Override
    public Collection<IInterpreterStatus> validateExpression(IInterpreterContext context, String expression) {
        return analyzeExpression(context, expression).getStatuses();
    }

    /**
     * Invalidate the current module, then rebuild and recompile it.
     */
    protected void invalidateModule() {
        module.invalidate();
    }

    /**
     * Retrieves the list of imports from the given interpreter context.
     *
     * @param context
     *            The context from which to retrieve a list of dependencies.
     */
    private void addContextImports(IInterpreterContext context) {
        final EObject element = context.getElement();
        if (element != null) {
            Resource resource = element.eResource();
            if (resource != null) {
                final URI uri = resource.getURI();
                if (AcceleoMTLInterpreter.existsInWorkspace(uri.toPlatformString(true))) {
                    viewpointProjects.add(uri.segment(1));
                } else if (AcceleoMTLInterpreter.existsInPlugins(uri.toPlatformString(true))) {
                    viewpointPlugins.add(uri.segment(1));
                }
            }
        }

        for (String dependency : context.getDependencies()) {
            addImport(dependency);
        }
    }

    /**
     * Asks the registered import handlers whether they know about the given dependency.
     *
     * @param dependency
     *            The dependency to try and add to this context.
     * @return <code>true</code> if we found a corresponding import handler and managed to create the dependency,
     *         <code>false</code> otherwise.
     */
    private boolean addExtendedImport(String dependency) {
        // Consider that this will always change the dependency if it previously
        // existed in the context
        boolean changedDependencies = false;
        final Iterator<ModuleDescriptor> currentDescriptors = extendedDependencies.iterator();
        while (currentDescriptors.hasNext() && !changedDependencies) {
            if (currentDescriptors.next().getQualifiedName().equals(dependency)) {
                changedDependencies = true;
                currentDescriptors.remove();
            }
        }

        final List<AbstractImportHandler> handlers = ImportHandlerRegistry.getRegisteredHandlers();
        boolean foundHandler = false;
        for (int i = 0; i < handlers.size() && !foundHandler; i++) {
            AbstractImportHandler importHandler = handlers.get(i);
            if (importHandler.canImport(viewpointPlugins, viewpointProjects, dependency)) {
                final ModuleDescriptor extendedModule = importHandler.createImport(viewpointPlugins, viewpointProjects, dependency);
                if (extendedModule != null) {
                    extendedDependencies.add(extendedModule);
                    foundHandler = true;
                }
            }
        }

        return changedDependencies || foundHandler;
    }

    /**
     * Tries and add the given dependency to the context, considering that it is an MTL file.
     *
     * @param dependency
     *            The dependency to try and add.
     * @return <code>true</code> if we found a corresponding Acceleo module and added the dependency, <code>false</code>
     *         otherwise.
     */
    private boolean addMTLImport(String dependency) {
        String actualDependency = dependency;
        final int dotIndex = actualDependency.lastIndexOf('.');
        // Whatever the extension, get rid of it. We only consider EMTL as valid
        // imports.
        if (dotIndex >= 0 && !actualDependency.substring(dotIndex).contains(IAcceleoConstants.NAMESPACE_SEPARATOR)) {
            actualDependency = actualDependency.substring(0, dotIndex);
        }
        String emtlPath = actualDependency.replaceAll(IAcceleoConstants.NAMESPACE_SEPARATOR, "/"); //$NON-NLS-1$
        emtlPath += '.' + IAcceleoConstants.EMTL_FILE_EXTENSION;

        Set<URI> candidates = Sets.union(findProjectCandidates(emtlPath), findPluginCandidates(emtlPath));

        /*
         * We may have multiple candidates for a single emtl path. Candidates located in the workspace will be
         * considered before candidates located in the plugins but, other than that, we let Acceleo do its resolution.
         */
        if (!candidates.isEmpty()) {
            return mtlDependencies.putAll(actualDependency, candidates);
        }
        return false;
    }

    /**
     * Validates that the given expression compiles in the given context.
     *
     * @param context
     *            Context of the current compilation.
     * @param compilationContext
     *            The Acceleo compilation context for this validation.
     * @return The compilation status as can be understood by viewpoint.
     */
    private Collection<IInterpreterStatus> doValidateExpression(IInterpreterContext context, CompilationContext compilationContext) {
        final QueryIdentifier query = module.ensureQueryExists(compilationContext);
        final CompilationResult compilationResult = module.compile(compilationContext, query);

        final Set<IInterpreterStatus> validationStatus = new LinkedHashSet<>();

        if (compilationResult.getStatus() != null && compilationResult.getStatus().getSeverity() != IStatus.OK) {
            if (compilationResult.getStatus() instanceof MultiStatus) {
                for (IStatus child : ((MultiStatus) compilationResult.getStatus()).getChildren()) {
                    final VariableType type = VariableType.fromString(compilationContext.getTargetType());
                    final String severity;
                    if (child.getSeverity() == IStatus.ERROR) {
                        severity = IInterpreterStatus.ERROR;
                    } else {
                        /*
                         * IInterpreterStatus only understand "ERROR" and "WARNING". Log infos as warnings.
                         */
                        severity = IInterpreterStatus.WARNING;
                    }

                    validationStatus.add(InterpreterStatusFactory.createInterpreterStatus(type, context.getField(), severity, child.getMessage(), 0, 0, 0));
                }
            }
        }

        return validationStatus;
    }

    /**
     * Checks whether a viewpoint specification plugin contains an emtl corresponding to the given path.
     *
     * @param emtlPath
     *            Path of the emtl for which we need potential URIs.
     * @return
     */
    private Set<URI> findPluginCandidates(String emtlPath) {
        Set<URI> result = new LinkedHashSet<>();

        for (String viewpointPluginSymbolicName : viewpointPlugins) {
            final Bundle viewpointPlugin = Platform.getBundle(viewpointPluginSymbolicName);
            if (viewpointPlugin != null) {
                final String fileSeparator = "/"; //$NON-NLS-1$
                final String fileName = emtlPath.substring(emtlPath.lastIndexOf(fileSeparator) + 1);
                // "fileSeparator" is also the bundle root.
                final Enumeration<URL> emtlEntries = viewpointPlugin.findEntries(fileSeparator, fileName, true);
                if (emtlEntries != null) {
                    while (emtlEntries.hasMoreElements()) {
                        final String candidatePath = emtlEntries.nextElement().getPath();

                        if (AcceleoMTLInterpreter.pathEndsWith(candidatePath, emtlPath)) {
                            final StringBuilder fullPath = new StringBuilder(viewpointPluginSymbolicName);
                            fullPath.append('/').append(candidatePath);

                            result.add(URI.createPlatformPluginURI(fullPath.toString(), true));
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * Checks if the workspace contains an IProject that could hold an emtl file located at the given path.
     *
     * @param emtlPath
     *            Path of the emtl for which we need potential URIs.
     * @return The list of all projects that could hold a file at the given path.
     */
    private Set<URI> findProjectCandidates(String emtlPath) {
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        final Set<URI> result = new LinkedHashSet<>();

        for (String viewpointProjectPath : viewpointProjects) {
            if (workspace.getRoot().exists(new Path(viewpointProjectPath))) {
                final IProject viewpointProject = workspace.getRoot().getProject(viewpointProjectPath);

                try {
                    final ResourceFinder emtlFinder = new ResourceFinder(emtlPath);
                    viewpointProject.accept(emtlFinder);

                    for (IResource candidate : emtlFinder.getMatches()) {
                        result.add(URI.createPlatformResourceURI(candidate.getFullPath().toString(), true));
                    }
                } catch (CoreException e) {
                    AcceleoMTLInterpreterPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, AcceleoMTLInterpreterPlugin.PLUGIN_ID, e.getMessage(), e));
                }
            }
        }

        return result;
    }

    /**
     * Evaluates the given expression and return the evaluation result and potential issues.
     *
     * @param context
     *            Target EObject of this evaluation.
     * @param expression
     *            Expression we are to evaluate.
     * @return The evaluation result along with all problems that may have occurred during evaluation.
     */
    private EvaluationResult internalEvaluate(EObject context, String expression) {
        final CompilationContext compilationContext = createCompilationContext(context, expression);

        final QueryIdentifier identifier = module.ensureQueryExists(compilationContext);
        CompilationResult compilationResult = module.compile(compilationContext, identifier);

        if (compilationResult.getStatus() == null || compilationResult.getStatus().getSeverity() == IStatus.OK) {
            EvaluationContext evaluationContext = new EvaluationContext(context, variables, compilationResult);
            return module.evaluate(evaluationContext);
        }
        return new EvaluationResult(null, compilationResult.getStatus());
    }

    /**
     * Create a new IIntepreter preparing the environment by registering the required EPackages and resource factories
     * in EMF.
     *
     * @return an {@link AcceleoMTLInterpreter} suitable for querying in a standalone environment.
     */
    public static AcceleoMTLInterpreter createStandaloneInterpreter() {
        AcceleoMTLInterpreter.registerPackages(EPackage.Registry.INSTANCE);
        AcceleoMTLInterpreter.registerResourceFactories(Resource.Factory.Registry.INSTANCE);
        AcceleoMTLInterpreter.registerLibraries();
        return new AcceleoMTLInterpreter();

    }

    private static void registerPackages(EPackage.Registry registry) {
        registry.put(EcorePackage.eINSTANCE.getNsURI(), EcorePackage.eINSTANCE);

        registry.put(org.eclipse.ocl.ecore.EcorePackage.eINSTANCE.getNsURI(), org.eclipse.ocl.ecore.EcorePackage.eINSTANCE);
        registry.put(ExpressionsPackage.eINSTANCE.getNsURI(), ExpressionsPackage.eINSTANCE);

        registry.put(MtlPackage.eINSTANCE.getNsURI(), MtlPackage.eINSTANCE);

        registry.put("http://www.eclipse.org/ocl/1.1.0/oclstdlib.ecore", //$NON-NLS-1$
                AcceleoMTLInterpreter.getOCLStdLibPackage());
    }

    private static void registerResourceFactories(Resource.Factory.Registry registry) {
        registry.getExtensionToFactoryMap().put("ecore", //$NON-NLS-1$
                new EcoreResourceFactoryImpl());
        registry.getContentTypeToFactoryMap().put(IAcceleoConstants.BINARY_CONTENT_TYPE, new EMtlBinaryResourceFactoryImpl());
        registry.getContentTypeToFactoryMap().put(IAcceleoConstants.XMI_CONTENT_TYPE, new EMtlResourceFactoryImpl());
        registry.getExtensionToFactoryMap().put(IAcceleoConstants.EMTL_FILE_EXTENSION, new EMtlResourceFactoryImpl());
    }

    private static EPackage getOCLStdLibPackage() {
        EcoreEnvironmentFactory factory = new EcoreEnvironmentFactory();
        EcoreEnvironment environment = (EcoreEnvironment) factory.createEnvironment();
        EPackage oclStdLibPackage = (EPackage) EcoreUtil.getRootContainer(environment.getOCLStandardLibrary().getBag());
        environment.dispose();
        return oclStdLibPackage;
    }

    private static void registerLibraries() {
        CodeSource acceleoModel = MtlPackage.class.getProtectionDomain().getCodeSource();
        if (acceleoModel != null) {
            String libraryLocation = acceleoModel.getLocation().toString();
            if (libraryLocation.endsWith(".jar")) { //$NON-NLS-1$
                libraryLocation = "jar:" + libraryLocation + '!'; //$NON-NLS-1$
            }

            URIConverter.URI_MAP.put(URI.createURI("http://www.eclipse.org/acceleo/mtl/3.0/mtlstdlib.ecore"), URI.createURI(libraryLocation + "/model/mtlstdlib.ecore")); //$NON-NLS-1$//$NON-NLS-2$
            URIConverter.URI_MAP.put(URI.createURI("http://www.eclipse.org/acceleo/mtl/3.0/mtlnonstdlib.ecore"), URI.createURI(libraryLocation + "/model/mtlnonstdlib.ecore")); //$NON-NLS-1$ //$NON-NLS-2$
        } else {
            throw new RuntimeException(Messages.AcceleoMTLInterpreter_acceleoModelPluginNotFound);
        }
    }

    @Override
    public ValidationResult analyzeExpression(IInterpreterContext context, String expression) {
        /*
         * The interpreter is created as a global singleton : one single interpreter for all operations, whatever the
         * number of VSM or representation files. Validation is called on expression granularity, and we are never
         * warned of the validation "start" or "end" events. We thus cannot keep any state for the in-memory module as
         * it could be reflecting operations located on other VSM files.
         */
        invalidateModule();

        ValidationResult result = new ValidationResult();

        final Map<String, String> validationVariables = new LinkedHashMap<>();
        for (Map.Entry<String, VariableType> contextVariable : context.getVariables().entrySet()) {
            final String varName = contextVariable.getKey();
            final VariableType varType = contextVariable.getValue();
            boolean isVarNameValid = varName != null && varName.length() > 0 && !varName.matches("[0-9]+"); //$NON-NLS-1$
            boolean isVarTypeValid = varType.hasDefinition();
            if (isVarNameValid && isVarTypeValid) {
                validationVariables.put(varName, varType.getCommonType(context.getAvailableEPackages()).getCompleteName(IAcceleoConstants.NAMESPACE_SEPARATOR));
            }
        }

        if (!context.requiresTargetType()) {
            final CompilationContext compilationContext = createCompilationContext(context, expression, "ecore::EObject", validationVariables); //$NON-NLS-1$
            result.addAllStatus(doValidateExpression(context, compilationContext));
        } else {

            VariableType candidateType = context.getTargetType();
            if (!candidateType.hasDefinition()) {
                String message = MessageFormat.format(Messages.AcceleoMTLInterpreter_domainClassNotFound, context.getField().getName());
                result.addStatus(InterpreterStatusFactory.createInterpreterStatus(context.getTargetType(), context.getField(), IInterpreterStatus.WARNING, message));
            } else {
                for (TypeName candidateTargetType : candidateType.getPossibleTypes()) {
                    final String expressionType = candidateTargetType.getCompleteName(IAcceleoConstants.NAMESPACE_SEPARATOR);

                    final CompilationContext compilationContext = createCompilationContext(context, expression, expressionType, validationVariables);
                    result.addAllStatus(doValidateExpression(context, compilationContext));
                }
            }
        }
        return result;
    }
}
